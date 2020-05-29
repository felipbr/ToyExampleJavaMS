package br.ufla.felipecb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import br.ufla.felipecb.bean.VendaBean;
import br.ufla.felipecb.entidades.ItemVenda;
import br.ufla.felipecb.entidades.Venda;
import br.ufla.felipecb.feignclient.InterfaceCliente;
import br.ufla.felipecb.feignclient.InterfaceFuncionario;
import br.ufla.felipecb.feignclient.InterfaceProduto;
import br.ufla.felipecb.vo.AutenticacaoVO;
import br.ufla.felipecb.vo.ClienteVO;
import br.ufla.felipecb.vo.FuncionarioVO;
import br.ufla.felipecb.vo.ProdutoVO;
import br.ufla.util.Util;

@Controller
@SessionScope
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	VendaBean vendaBean;
	
	@Autowired
	private InterfaceCliente interfaceCliente;
	
	@Autowired
	private InterfaceProduto interfaceProduto;
	
	@Autowired
	private InterfaceFuncionario interfaceFunc;
	
	//Mantem na seção
	FuncionarioVO funcionario;
	Venda venda;
	List<ProdutoVO> produtos;
	
	//Autenticacao
	@RequestMapping(value = "/autenticacao")
    public String conectar(Model model) {

		model.addAttribute("autenticacaoVO", new AutenticacaoVO());
		return "login";
	}
	
	@PostMapping(value = "/autenticar")
    public String conectar(@ModelAttribute AutenticacaoVO autenticacao, Model model) {
		
		try {
			funcionario = interfaceFunc.autenticarFuncionario(autenticacao.getLogin(), Util.get_SHA_256(autenticacao.getSenha()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(funcionario == null) {
			model.addAttribute("erro", "Login ou senha inválido");
			return "login";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/desconectar")
    public String desconectar(Model model) {

		limparSessao();
		funcionario = null;
		
		return "redirect:/venda/index";
	}
	//fim dos metodos de autenticacao
	
	//logica compra
	@RequestMapping({ "", "/", "/index" })
    public String login(Model model) {
		if(funcionario == null) {
			return "redirect:/venda/autenticacao";
		}
		return "index";
	}
	
	@RequestMapping("/criar")
    public String criar(Model model) {
		venda = new Venda();
		venda.setFuncionario(funcionario);
		
    	model.addAttribute("venda", venda);
    	model.addAttribute("cliente", new ClienteVO());

    	return "cadastroVenda";
    }
	
	@PostMapping(value = "/carregarVenda")
    public String carregarVenda(@ModelAttribute("venda.cliente") ClienteVO cli, ModelMap model) {
		
		ClienteVO cliente = interfaceCliente.recuperarClientePorCPF(cli.getCpf());

		if(cliente != null) {
			venda.setIdCliente(cliente.getId());
			venda.setCliente(cliente);
			
			venda.setIdFuncionario(funcionario.getId());
			venda.setFuncionario(funcionario);
			
			venda.setListaItens(new ArrayList<ItemVenda>());
			produtos = (List<ProdutoVO>) interfaceProduto.obterProdutos();
	    	
			model.addAttribute("venda", venda);	
	    	model.addAttribute("produtos", produtos);

	    	model.addAttribute("item", new ItemVenda());
			
		} else {
			model.addAttribute("erro", "Cliente não cadastrado");
			model.addAttribute("cliente", cli);
		}
		
		return "cadastroVenda";
	}
	
	@PostMapping("/adicionarItem")
    public String adicionarItem(@ModelAttribute ItemVenda item, Model model) {
		
		if(item.getQuantidade() > 0) {
			//Preenche o produto no objeto
			for(ProdutoVO prod : produtos) {
				if(prod.getId().equals(item.getIdProduto())) {
					item.setProduto(prod);
					//armazerna o valor do produto na época comprada
					item.setValorUnidade(prod.getValor());
					item.setVenda(venda);
					break;
				}
			}
			
			//Verifica duplicadas
			boolean jaAdicionado = false;
			for(ItemVenda iv: venda.getListaItens()) {
				if(iv.getIdProduto().equals(item.getIdProduto())) {
					jaAdicionado = true;
					break;
				}
			}
			//Caso já tenha sido adicionado na sacola lança erro
			if(jaAdicionado) {
				model.addAttribute("erro", "Este produto já está na sacola");	
				model.addAttribute("item", item);
			} else {
				venda.getListaItens().add(item);
		    	model.addAttribute("item", new ItemVenda());
			}
		} else {
			model.addAttribute("erro", "A quantidade deve ser maior que 0");	
			model.addAttribute("item", item);
		}
		
		model.addAttribute("venda", venda);	
    	model.addAttribute("produtos", produtos);
    	
    	
		
		return "cadastroVenda";
	}
	
	@PostMapping("/salvar")
    public String salvar(Model model) {
		
		if(venda.getListaItens().isEmpty()) {
			model.addAttribute("venda", venda);	
	    	model.addAttribute("produtos", produtos);
	    	model.addAttribute("item", new ItemVenda());
	    	
			model.addAttribute("erro", "É necessário adicionar ao menos um item");
	    	
			return "cadastroVenda";
		}
		
		for(ItemVenda iv : venda.getListaItens()) {
			interfaceProduto.atualizarEstoque(iv.getIdProduto(), iv.getQuantidade());
		}
		
		venda = vendaBean.salvar(venda);
		model.addAttribute("mensagem", "Venda realizada");
		
		limparSessao();
		
		return "index";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
    	
    	model.addAttribute("vendas", vendaBean.buscarVendas());
		
		return "vendas";
	}
	
	@RequestMapping("/consultar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    	
		Venda venda = vendaBean.buscarVenda(id);
		venda.setFuncionario(interfaceFunc.recuperarFunconarioPorId(venda.getIdFuncionario()));
		venda.setCliente(interfaceCliente.recuperarClientePorId(venda.getIdCliente()));
    	for(ItemVenda iv : venda.getListaItens()) {
    		iv.setProduto(interfaceProduto.recuperarProdutoPorId(iv.getIdProduto()));
    	}
    	
    	model.addAttribute("venda", venda);
    	

    	return "cadastroVenda";
    }

	@RequestMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, Model model) {
    	
		vendaBean.excluir(id);

    	return "redirect:/venda/listar";
    }
	
	private void limparSessao() {
		venda = null;
		produtos = null;
	}
	
}

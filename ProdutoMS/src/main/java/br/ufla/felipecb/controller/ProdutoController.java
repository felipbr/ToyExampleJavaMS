package br.ufla.felipecb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufla.felipecb.bean.ProdutoBean;
import br.ufla.felipecb.entidades.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoBean produtoBean;

	@RequestMapping("/criar")
    public String criar(Model model) {
    	
    	model.addAttribute("produto", new Produto());

    	return "cadastroProduto";
    }
	
	@RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    	
		Produto produto = produtoBean.buscarProduto(id);
    	model.addAttribute("produto", produto);

    	return "cadastroProduto";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto, Model model) {
		
		produto = produtoBean.salvar(produto);
		
		model.addAttribute("mensagem", "Produto "+ produto.getNome()+ " atualizado");
		model.addAttribute("produto", new Produto());
		
		return "cadastroProduto";
    		
	}
	
	@PostMapping("/alterar")
    public String alterar(@ModelAttribute Produto produto, Model model) {
    	
    	produtoBean.salvar(produto);
		
    	model.addAttribute("mensagem", "Produto "+ produto.getNome()+ " atualizado");
		model.addAttribute("produto", new Produto());
		
		return "cadastroProduto";
    }

	@RequestMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, Model model) {
    	
		produtoBean.excluir(id);

    	return "redirect:/produto/listar";
    }
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	private String listar(Model model) {
    	
    	model.addAttribute("produtos", produtoBean.buscarProdutos());
		
		return "produtos";
	}
	
	@ResponseBody
	@RequestMapping(value = "/atualizarEstoque/{idProduto}/{quantidade}")
	private Boolean atualizaEstoque(@PathVariable("idProduto") Long id, @PathVariable("quantidade") Integer quantidade) {
		
		return produtoBean.atualizarEstoque(id, quantidade);
	}
	
	@ResponseBody
	@RequestMapping(value = "/obterProdutos", method = RequestMethod.GET)
	private Iterable<Produto> obterProdutos() {
    	return produtoBean.buscarProdutos();
	}
	
	@ResponseBody
	@RequestMapping(value = "/recuperarPorId/{id}", method = RequestMethod.GET)
	public Produto recuperarPorId(@PathVariable("id") Long idProduto) {
		return produtoBean.buscarProduto(idProduto);
	}


}

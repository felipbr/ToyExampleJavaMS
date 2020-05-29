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

import br.ufla.felipecb.bean.ClienteBean;
import br.ufla.felipecb.entidades.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteBean clienteBean;

	@RequestMapping("/criar")
    public String criar(Model model) {
    	
    	model.addAttribute("cliente", new Cliente());

    	return "cadastroCliente";
    }
	
	@RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    	
		Cliente cliente = clienteBean.buscarCliente(id);
    	model.addAttribute("cliente", cliente);

    	return "cadastroCliente";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente, Model model) {
		
		cliente = clienteBean.salvar(cliente);
		
		model.addAttribute("mensagem", "Cliente "+ cliente.getNome()+ " atualizado");
		model.addAttribute("cliente", new Cliente());
		
		return "cadastroCliente";
    		
	}
	
	@PostMapping("/alterar")
    public String alterar(@ModelAttribute Cliente cliente, Model model) {
    	
    	clienteBean.salvar(cliente);
		
    	model.addAttribute("mensagem", "Cliente "+ cliente.getNome()+ " atualizado");
		model.addAttribute("cliente", new Cliente());
		
		return "cadastroCliente";
    }

	@RequestMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, Model model) {
    	
		clienteBean.excluir(id);

    	return "redirect:/cliente/listar";
    }
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	private String listar(Model model) {
    	
    	model.addAttribute("clientes", clienteBean.buscarClientes());
		
		return "clientes";
	}
	
	@ResponseBody
	@RequestMapping("/recuperarPorCPF/{cpf}")
    public Cliente ecuperarPorCPF(@PathVariable("cpf") String cpf, Model model) {
    	return clienteBean.buscarPorCPF(cpf);
    }
	
	@ResponseBody
	@RequestMapping("/recuperarPorId/{id}")
	public Cliente ecuperarPorId(@PathVariable("id") Long id) {
		return clienteBean.buscarCliente(id);
	}
	
}

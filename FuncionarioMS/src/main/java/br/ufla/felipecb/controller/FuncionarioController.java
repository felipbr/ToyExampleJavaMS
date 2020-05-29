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

import br.ufla.felipecb.bean.FuncionarioBean;
import br.ufla.felipecb.entidades.Funcionario;
import br.ufla.util.Util;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioBean funcionarioBean;

	@RequestMapping("/criar")
    public String criar(Model model) {
    	
    	model.addAttribute("funcionario", new Funcionario());

    	return "cadastroFuncionario";
    }
	
	@RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    	
		Funcionario funcionario = funcionarioBean.buscarFuncionario(id);
    	model.addAttribute("funcionario", funcionario);

    	return "cadastroFuncionario";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute Funcionario funcionario, Model model) {
		
		funcionario.setSenha(Util.get_SHA_256(funcionario.getSenha()));
		funcionario = funcionarioBean.salvar(funcionario);
		
		model.addAttribute("mensagem", "Funcionario "+ funcionario.getNome()+ " atualizado");
		model.addAttribute("funcionario", new Funcionario());
		
		return "cadastroFuncionario";
    		
	}
	
	@PostMapping("/alterar")
    public String alterar(@ModelAttribute Funcionario funcionario, Model model) {
    	
    	funcionarioBean.salvar(funcionario);
		
    	model.addAttribute("mensagem", "Funcionario "+ funcionario.getNome()+ " atualizado");
		model.addAttribute("funcionario", new Funcionario());
		
		return "cadastroFuncionario";
    }

	@RequestMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, Model model) {
    	
		funcionarioBean.excluir(id);

    	return "redirect:/funcionario/listar";
    }
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	private String listar(Model model) {
    	
    	model.addAttribute("funcionarios", funcionarioBean.buscarFuncionarios());
		
		return "funcionarios";
	}
	

	@ResponseBody
	@RequestMapping(value="/recuperarPorId/{id}")
	public Funcionario recuperarPorId(@PathVariable("id") Long id) {
		return funcionarioBean.buscarFuncionario(id);
	}
	
}

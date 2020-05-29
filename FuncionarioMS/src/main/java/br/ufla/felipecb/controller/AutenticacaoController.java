package br.ufla.felipecb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufla.felipecb.bean.FuncionarioBean;
import br.ufla.felipecb.entidades.Funcionario;
import br.ufla.util.Util;

@Controller
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	private Funcionario funcionario;
	
	@Autowired
	FuncionarioBean funcionarioBean;
	
	@RequestMapping("")
    public String inicio(Model model) {
		model.addAttribute("login","");
		model.addAttribute("senha","");
    	return "login";
    }
	
	@RequestMapping(value = "/conectar", method = RequestMethod.POST)
    public String conectar(@RequestParam("login") String login, @RequestParam("senha") String senha, Model model) {
		
		funcionario = funcionarioBean.logar(login, Util.get_SHA_256(senha));
		
		if(funcionario == null) {
			model.addAttribute("login", login);
			model.addAttribute("senha", senha);
			model.addAttribute("mensagem", "Login ou senha inválido");
			return "login";
		}
		
		model.addAttribute("mensagem", "Funcionario "+ funcionario.getNome()+ " autenticado");
		
		return "index";
    		
	}
	
	@PostMapping("/desconectar")
    public String desconectar(@ModelAttribute Funcionario funcionario, Model model) {
    	
		funcionario = null;
		
		return "login";
    }
	
	@ResponseBody
	@RequestMapping("/autenticarFunc/{login}/{senha}")
	public Funcionario autenticarFunc(@PathVariable("login") String login, @PathVariable("senha") String senha) {
		Funcionario funcionario = funcionarioBean.logar(login, senha);
		
		if(funcionario != null) {
			return funcionario;
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public Funcionario autenticarFuncionario(@RequestBody Map<String, String> json) {
		Funcionario funcionario = funcionarioBean.logar(json.get("login"), json.get("senha"));
		
		if(funcionario != null) {
			return funcionario;
		}
		return null;
	}
	
}

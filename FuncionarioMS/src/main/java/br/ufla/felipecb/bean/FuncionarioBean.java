package br.ufla.felipecb.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufla.felipecb.dao.FuncionarioRepository;
import br.ufla.felipecb.entidades.Funcionario;

@Component
public class FuncionarioBean {

	@Autowired
	FuncionarioRepository funcionarioDao;
	
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioDao.save(funcionario);
	}
	
	public Iterable<Funcionario> buscarFuncionarios() {
		return funcionarioDao.findAll();
	}

	public Funcionario buscarFuncionario(Long id) {
		return funcionarioDao.findById(id).get();
	}

	public void excluir(Long id) {
		funcionarioDao.deleteById(id);
	}

	public Funcionario logar(String login, String senha) {
		return funcionarioDao.logar(login, senha);
	}
}

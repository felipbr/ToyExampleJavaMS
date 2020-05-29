package br.ufla.felipecb.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufla.felipecb.entidades.Funcionario;

@Repository
@Transactional
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{

	@Query("SELECT func FROM Funcionario func WHERE func.login = :login and func.senha = :senha")
	Funcionario logar(String login, String senha);
	
	
}
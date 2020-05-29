package br.ufla.felipecb.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.ufla.felipecb.entidades.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	@Query("SELECT cli FROM Cliente cli WHERE cli.cpf = :cpf")
	Cliente buscarPorCPF(String cpf);
	
	
}
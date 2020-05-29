package br.ufla.felipecb.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufla.felipecb.entidades.Venda;

@Repository
@Transactional
public interface VendaRepository extends CrudRepository<Venda, Long>{
	
	
}
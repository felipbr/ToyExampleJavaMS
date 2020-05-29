package br.ufla.felipecb.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufla.felipecb.entidades.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Produto set quantidade = ( "
				+ " SELECT (quantidade - :quantidade) FROM Produto WHERE id = :id"
			+ ") WHERE id = :id and quantidade >= :quantidade")
	Integer atualizarEstoque(Long id, Integer quantidade);
	
	
}
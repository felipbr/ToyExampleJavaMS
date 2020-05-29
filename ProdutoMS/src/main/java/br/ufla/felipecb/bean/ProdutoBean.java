package br.ufla.felipecb.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufla.felipecb.dao.ProdutoRepository;
import br.ufla.felipecb.entidades.Produto;

@Component
public class ProdutoBean {

	@Autowired
	ProdutoRepository produtoDao;
	
	public Produto salvar(Produto produto) {
		return produtoDao.save(produto);
	}
	
	public Iterable<Produto> buscarProdutos() {
		return produtoDao.findAll();
	}

	public Produto buscarProduto(Long id) {
		return produtoDao.findById(id).get();
	}

	public void excluir(Long id) {
		produtoDao.deleteById(id);
	}

	public Boolean atualizarEstoque(Long id, Integer quantidade) {
		return produtoDao.atualizarEstoque(id, quantidade) > 0;
	}
}

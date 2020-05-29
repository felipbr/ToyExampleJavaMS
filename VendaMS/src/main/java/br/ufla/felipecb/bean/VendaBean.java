package br.ufla.felipecb.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufla.felipecb.dao.VendaRepository;
import br.ufla.felipecb.entidades.Venda;

@Component
public class VendaBean {

	@Autowired
	VendaRepository vendaDao;
	
	public Venda salvar(Venda venda) {
		return vendaDao.save(venda);
	}
	
	public Iterable<Venda> buscarVendas() {
		return vendaDao.findAll();
	}
	
	public Iterable<Venda> buscarVendasPorCliente(String CPF) {
		return vendaDao.findAll();
	}

	public Venda buscarVenda(Long id) {
		return vendaDao.findOne(id);
	}

	public void excluir(Long id) {
		vendaDao.delete(id);
	}
}

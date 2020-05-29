package br.ufla.felipecb.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufla.felipecb.dao.ClienteRepository;
import br.ufla.felipecb.entidades.Cliente;

@Component
public class ClienteBean {

	@Autowired
	ClienteRepository clienteDao;
	
	public Cliente salvar(Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	public Iterable<Cliente> buscarClientes() {
		return clienteDao.findAll();
	}

	public Cliente buscarCliente(Long id) {
		return clienteDao.findById(id).get();
	}

	public void excluir(Long id) {
		clienteDao.deleteById(id);
	}

	public Cliente buscarPorCPF(String cpf) {
		return clienteDao.buscarPorCPF(cpf);
	}
}

package br.ufla.felipecb.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.felipecb.vo.ClienteVO;

@FeignClient(value="cliente-ms") // nome do serviço
public interface InterfaceCliente {
	//Definir interface
	
	@RequestMapping( method = RequestMethod.GET, value="/cliente/recuperarPorCPF/{cpf}")
	public ClienteVO recuperarClientePorCPF(@PathVariable("cpf") String cpf);

	@RequestMapping( method = RequestMethod.GET, value="/cliente/recuperarPorId/{id}")
	public ClienteVO recuperarClientePorId(@PathVariable("id") Long id);
	
}

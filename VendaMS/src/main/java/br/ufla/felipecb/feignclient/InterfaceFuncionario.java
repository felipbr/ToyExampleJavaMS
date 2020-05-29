package br.ufla.felipecb.feignclient;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.felipecb.vo.FuncionarioVO;

@FeignClient(value="funcionario-ms") // nome do serviço
public interface InterfaceFuncionario {

	@RequestMapping( method = RequestMethod.POST, value="/autenticacao/autenticar/")
	public Long autenticarFuncionario(@RequestBody Map<String, String> json);
	
	@RequestMapping( method = RequestMethod.GET, value="/autenticacao/autenticarFunc/{login}/{senha}")
	public FuncionarioVO autenticarFuncionario(@PathVariable("login") String login, @PathVariable("senha") String senha);

	@RequestMapping( method = RequestMethod.GET, value="/funcionario/recuperarPorId/{id}")
	public FuncionarioVO recuperarFunconarioPorId(@PathVariable("id") Long id);
	
}

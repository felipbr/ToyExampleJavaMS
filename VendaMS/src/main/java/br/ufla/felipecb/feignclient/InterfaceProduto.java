package br.ufla.felipecb.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.felipecb.vo.ProdutoVO;

@FeignClient(value="produto-ms")
public interface InterfaceProduto {
	// Obter todos os produtos cadastrados 
	@RequestMapping( method = RequestMethod.GET, value="/produto/obterProdutos")
	public Iterable<ProdutoVO> obterProdutos();
	
	@RequestMapping( method = RequestMethod.GET, value="/produto/atualizarEstoque/{idProduto}/{quantidade}")
	public Boolean atualizarEstoque(@PathVariable("idProduto") Long idProduto, @PathVariable("quantidade") Integer qnt);

	@GetMapping(value="/produto/recuperarPorId/{id}")
	public ProdutoVO recuperarProdutoPorId(@PathVariable("id") Long idProduto);
	
}

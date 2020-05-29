package br.ufla.felipecb.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ufla.felipecb.vo.ClienteVO;
import br.ufla.felipecb.vo.FuncionarioVO;

@Entity
@Table(name="venda")
public class Venda {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence_id_venda"
		)
		@SequenceGenerator(
			name =  "sequence_id_venda",
			sequenceName = "sequence_venda"
		)
	private Long id;
	
	@Column(name="valor")
	private float valor;
	
	@Column(name="tipo_pagamento")
	private String tipoPagamento;
	
	@Column(name="id_cliente")
	private Long idCliente;
	
	@Transient
	private ClienteVO cliente;
	
	@Column(name="id_funcionario")
	private Long idFuncionario;

	@Transient
	private FuncionarioVO funcionario;
	
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<ItemVenda> listaItens;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemVenda> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<ItemVenda> listaItens) {
		this.listaItens = listaItens;
	}
	
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
	
	public FuncionarioVO getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getValorTotal() {
		Double total = new Double(0l);
		for(ItemVenda iv : listaItens) {
			total += iv.getValorUnidade() * iv.getQuantidade();
		}
		
		return total.toString();
	}
}

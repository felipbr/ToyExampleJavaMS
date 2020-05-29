package br.ufla.felipecb.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence_id_produto"
		)
		@SequenceGenerator(
			name =  "sequence_id_id_produto",
			sequenceName = "sequence_produto"
		)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="valor")
	private Float valor;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@Column(name="descricao")
	private String descricao;
	
	public Produto () { }
	
	public Produto(Long id, String nome, Float valor, Integer quantidade, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}
	public Produto (Long id, int quantidade) {
		this.id = id;
		this.quantidade = quantidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}

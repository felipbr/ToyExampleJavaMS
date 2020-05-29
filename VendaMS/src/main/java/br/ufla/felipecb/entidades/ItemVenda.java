package br.ufla.felipecb.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ufla.felipecb.vo.ProdutoVO;

@Entity
@Table(name="item_venda")
public class ItemVenda {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence_id_item_venda"
		)
		@SequenceGenerator(
			name =  "sequence_id_item_venda",
			sequenceName = "sequence_item_venda"
		)
	private Long id;
	
	@Column(name="valor_unidade")
	private float valorUnidade;

	@Column(name="quantidade")
	private int quantidade;
	
	@Column(name="id_produto")
	private Long idProduto;
	
	@Transient
	private ProdutoVO produto;
	
	@ManyToOne
	@JoinColumn(name="id_venda")
	private Venda venda;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public float getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(float valorUnidade) {
		this.valorUnidade = valorUnidade;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	
	
}

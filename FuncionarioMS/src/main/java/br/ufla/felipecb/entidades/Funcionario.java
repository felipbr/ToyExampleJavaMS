package br.ufla.felipecb.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence_id_funcionario"
		)
		@SequenceGenerator(
			name =  "sequence_id_id_funcionario",
			sequenceName = "sequence_funcionario"
		)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cargo")
	private String cargo;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	public Funcionario () { }

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

	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}

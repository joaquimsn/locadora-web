package br.com.jsn.noleggio.modules.endereco.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(name="endereco")
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = -5208538687499580299L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String cep;

	private String endereco;

	//bi-directional many-to-one association to Bairro
	@ManyToOne
	@JoinColumn(name="id_bairro")
	private Bairro bairro;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;

	public Endereco() {
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
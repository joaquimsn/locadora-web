package br.com.jsn.noleggio.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@Table(name="cidade")
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cidade")
	private int idCidade;

	private String cidade;

	@Column(name="codigo_ibge")
	private int codigoIbge;

	//bi-directional many-to-one association to Bairro
	@OneToMany(mappedBy="cidade")
	private List<Bairro> bairros;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumn(name="uf")
	private Uf ufBean;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="cidade")
	private List<Endereco> enderecos;

	public Cidade() {
	}

	public int getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getCodigoIbge() {
		return this.codigoIbge;
	}

	public void setCodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public List<Bairro> getBairros() {
		return this.bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public Bairro addBairro(Bairro bairro) {
		getBairros().add(bairro);
		bairro.setCidade(this);

		return bairro;
	}

	public Bairro removeBairro(Bairro bairro) {
		getBairros().remove(bairro);
		bairro.setCidade(null);

		return bairro;
	}

	public Uf getUfBean() {
		return this.ufBean;
	}

	public void setUfBean(Uf ufBean) {
		this.ufBean = ufBean;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setCidade(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setCidade(null);

		return endereco;
	}

}
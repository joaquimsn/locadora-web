package br.com.jsn.noleggio.modules.endereco.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	@OneToMany(mappedBy="cidade", fetch = FetchType.LAZY)
	private List<Bairro> bairros;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumn(name="uf")
	private Uf ufBean;

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

	public Uf getUfBean() {
		return this.ufBean;
	}

	public void setUfBean(Uf ufBean) {
		this.ufBean = ufBean;
	}
}
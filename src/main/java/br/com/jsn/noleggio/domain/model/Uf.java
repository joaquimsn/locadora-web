package br.com.jsn.noleggio.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uf database table.
 * 
 */
@Entity
@Table(name="uf")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String uf;

	@Column(name="codigo_ibge")
	private int codigoIbge;

	private String estado;

	//bi-directional many-to-one association to Cidade
	@OneToMany(mappedBy="ufBean")
	private List<Cidade> cidades;

	public Uf() {
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getCodigoIbge() {
		return this.codigoIbge;
	}

	public void setCodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade addCidade(Cidade cidade) {
		getCidades().add(cidade);
		cidade.setUfBean(this);

		return cidade;
	}

	public Cidade removeCidade(Cidade cidade) {
		getCidades().remove(cidade);
		cidade.setUfBean(null);

		return cidade;
	}

}
package br.com.jsn.noleggio.modules.endereco.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the uf database table.
 * 
 */
@Entity
@Table(name="uf")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 5411104875287668164L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String uf;

	@Column(name="codigo_ibge")
	private int codigoIbge;

	private String estado;

	@Transient
	private List<String> listaCidadeString;

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

	public List<String> getListaCidadeString() {
		return listaCidadeString;
	}

	public void setListaCidadeString(List<String> listaCidadeString) {
		this.listaCidadeString = listaCidadeString;
	}
}
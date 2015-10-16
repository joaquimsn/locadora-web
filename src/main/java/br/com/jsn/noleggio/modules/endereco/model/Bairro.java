package br.com.jsn.noleggio.modules.endereco.model;

 import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the bairro database table.
 * 
 */
@Entity
@Table(name="bairro")
@NamedQuery(name="Bairro.findAll", query="SELECT b FROM Bairro b")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_bairro")
	private int idBairro;

	private String bairro;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="bairro")
	private List<Endereco> enderecos;

	public Bairro() {
	}

	public int getIdBairro() {
		return this.idBairro;
	}

	public void setIdBairro(int idBairro) {
		this.idBairro = idBairro;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setBairro(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setBairro(null);

		return endereco;
	}

}
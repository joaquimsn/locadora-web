package br.com.jsn.noleggio.domain.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the acessorio_veiculo database table.
 */
@Entity
@Table(name = "acessorio_veiculo")
@NamedQuery(name = "AcessorioVeiculo.findAll", query = "SELECT a FROM AcessorioVeiculo a")
public class AcessorioVeiculo implements Serializable {
	private static final long serialVersionUID = -1491088975554364469L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_acessorio")
	private int idAcessorio;

	@Column(name = "codigo_acessorio")
	private int codigoAcessorio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	// bi-directional many-to-one association to Veiculo
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;

	public AcessorioVeiculo() {
	}

	public int getIdAcessorio() {
		return this.idAcessorio;
	}

	public void setIdAcessorio(int idAcessorio) {
		this.idAcessorio = idAcessorio;
	}

	public int getCodigoAcessorio() {
		return this.codigoAcessorio;
	}

	public void setCodigoAcessorio(int codigoAcessorio) {
		this.codigoAcessorio = codigoAcessorio;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAcessorio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AcessorioVeiculo other = (AcessorioVeiculo) obj;
		if (idAcessorio != other.idAcessorio) {
			return false;
		}
		return true;
	}
}
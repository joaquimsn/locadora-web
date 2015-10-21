package br.com.jsn.noleggio.modules.locacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CPF;

/**
 * The persistent class for the pagamento database table.
 * 
 */
@Entity
@Table(name = "pagamento")
@NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = -488752121065302961L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pagamento")
	private int idPagamento;
	
	@CPF
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento")
	private Date dataPagamento;

	@Column(name = "nome_titular")
	private String nomeTitular;

	private String tipo;

	private double valor;

	// bi-directional many-to-one association to Locacao
	@ManyToOne
	@JoinColumn(name = "id_locacao")
	private Locacao locacao;

	public Pagamento() {
	}

	public int getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNomeTitular() {
		return this.nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Locacao getLocacao() {
		return this.locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPagamento;
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
		Pagamento other = (Pagamento) obj;
		if (idPagamento != other.idPagamento) {
			return false;
		}
		return true;
	}
}
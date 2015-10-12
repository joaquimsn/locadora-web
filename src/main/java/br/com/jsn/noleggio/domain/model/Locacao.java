package br.com.jsn.noleggio.domain.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the locacao database table.
 * 
 */
@Entity
@Table(name = "locacao")
@NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l")
public class Locacao implements Serializable {
	private static final long serialVersionUID = -1879760119817965354L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_locacao")
	private int idLocacao;

	@Column(name = "agencia_devolucao")
	private int agenciaDevolucao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_devolucao")
	private Date dataHoraDevolucao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_locacao")
	private Date dataHoraLocacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_prevista_devolucao")
	private Date dataHoraPrevistaDevolucao;

	@Column(name = "id_agencia")
	private int idAgencia;

	@Column(name = "id_cliente")
	private int idCliente;

	@Column(name = "id_funcionario")
	private int idFuncionario;

	@Column(name = "id_pagamento")
	private int idPagamento;

	@Column(name = "km_devolucao")
	private int kmDevolucao;

	@Column(name = "km_locacao")
	private double kmLocacao;

	private int status;

	@Column(name = "tipo_tarifa")
	private int tipoTarifa;

	private double valor;

	@Column(name = "valor_acrescimo")
	private double valorAcrescimo;

	// bi-directional many-to-one association to Veiculo
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;

	// bi-directional many-to-one association to Pagamento
	@OneToMany(mappedBy = "locacao")
	private List<Pagamento> listaPagamento;

	public Locacao() {
	}

	public int getIdLocacao() {
		return this.idLocacao;
	}

	public void setIdLocacao(int idLocacao) {
		this.idLocacao = idLocacao;
	}

	public int getAgenciaDevolucao() {
		return this.agenciaDevolucao;
	}

	public void setAgenciaDevolucao(int agenciaDevolucao) {
		this.agenciaDevolucao = agenciaDevolucao;
	}

	public Date getDataHoraDevolucao() {
		return this.dataHoraDevolucao;
	}

	public void setDataHoraDevolucao(Date dataHoraDevolucao) {
		this.dataHoraDevolucao = dataHoraDevolucao;
	}

	public Date getDataHoraLocacao() {
		return this.dataHoraLocacao;
	}

	public void setDataHoraLocacao(Date dataHoraLocacao) {
		this.dataHoraLocacao = dataHoraLocacao;
	}

	public Date getDataHoraPrevistaDevolucao() {
		return this.dataHoraPrevistaDevolucao;
	}

	public void setDataHoraPrevistaDevolucao(Date dataHoraPrevistaDevolucao) {
		this.dataHoraPrevistaDevolucao = dataHoraPrevistaDevolucao;
	}

	public int getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdFuncionario() {
		return this.idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public int getKmDevolucao() {
		return this.kmDevolucao;
	}

	public void setKmDevolucao(int kmDevolucao) {
		this.kmDevolucao = kmDevolucao;
	}

	public double getKmLocacao() {
		return this.kmLocacao;
	}

	public void setKmLocacao(double kmLocacao) {
		this.kmLocacao = kmLocacao;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTipoTarifa() {
		return this.tipoTarifa;
	}

	public void setTipoTarifa(int tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorAcrescimo() {
		return this.valorAcrescimo;
	}

	public void setValorAcrescimo(double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Pagamento> getListaPagamento() {
		return this.listaPagamento;
	}

	public void setListaPagamento(List<Pagamento> listaPagamento) {
		this.listaPagamento = listaPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAgencia;
		result = prime * result + idCliente;
		result = prime * result + idLocacao;
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
		Locacao other = (Locacao) obj;
		if (idAgencia != other.idAgencia) {
			return false;
		}
		if (idCliente != other.idCliente) {
			return false;
		}
		if (idLocacao != other.idLocacao) {
			return false;
		}
		return true;
	}
}
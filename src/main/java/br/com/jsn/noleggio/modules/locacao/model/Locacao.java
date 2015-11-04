package br.com.jsn.noleggio.modules.locacao.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.jsn.noleggio.main.util.DateUtil;
import br.com.jsn.noleggio.main.util.SystemUtil;
import br.com.jsn.noleggio.main.validation.BusinessValidation;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.veiculo.enums.TipoTarifaEnum;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;
import br.com.jsn.noleggio.modules.veiculo.pattern.VeiculoNullObject;

/**
 * The persistent class for the locacao database table.
 * 
 */
@Entity
@Table(name = "locacao")
@NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l")
public class Locacao extends BusinessValidation implements Serializable {
	private static final long serialVersionUID = -1879760119817965354L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_locacao")
	private int idLocacao;

	@Column(name = "agencia_devolucao")
	private Agencia agenciaDevolucao;

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
	private Agencia agencia;

	@Column(name = "id_cliente")
	private Cliente cliente;

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
	@OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
	private List<Pagamento> listaPagamento;

	public Locacao() {
	}

	public int getIdLocacao() {
		return this.idLocacao;
	}

	public void setIdLocacao(int idLocacao) {
		this.idLocacao = idLocacao;
	}

	public Agencia getAgenciaDevolucao() {
		return agenciaDevolucao;
	}

	public void setAgenciaDevolucao(Agencia agenciaDevolucao) {
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

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public StatusLocacaoEnum getStatus() {
		if (StatusLocacaoEnum.ENCERRADA.equals(StatusLocacaoEnum.getEnumByValue(status))) {
			return StatusLocacaoEnum.ENCERRADA;
		}
		
		if (StatusLocacaoEnum.ABERTA.equals(StatusLocacaoEnum.getEnumByValue(status))) {
			if (getDataHoraPrevistaDevolucao().before(new Date())) {
				return StatusLocacaoEnum.ATRASADA;
			}
		}
		
		return StatusLocacaoEnum.ABERTA;
	}

	public void setStatus(StatusLocacaoEnum statusLocacaoEnum) {
		this.status = statusLocacaoEnum.getValue();
	}

	public TipoTarifaEnum getTipoTarifa() {
		return TipoTarifaEnum.getEnumByValue(tipoTarifa);
	}

	public void setTipoTarifa(TipoTarifaEnum tipoTarifaEnum) {
		this.tipoTarifa = tipoTarifaEnum.getValue();
	}

	public double getValor() {
		calcularPrecoLocacao();
		return this.valor;
	}

	public double getValorAcrescimo() {
		return this.valorAcrescimo;
	}

	public void setValorAcrescimo(double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}

	public Veiculo getVeiculo() {
		if (veiculo == null) {
			return new VeiculoNullObject();
		}
		
		return veiculo;
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
	
	public void addPagamento(Pagamento pagamento) {
		if (listaPagamento == null) {
			listaPagamento = new ArrayList<Pagamento>();
		}
		
		pagamento.setLocacao(this);
		listaPagamento.add(pagamento);
	}
	
	private void calcularPrecoLocacao() {
		int quantidadeDia = DateUtil.intervalInDays(dataHoraLocacao, dataHoraPrevistaDevolucao);
		if (quantidadeDia == 0) {
			quantidadeDia = 1;
		}
		valor = quantidadeDia * getVeiculo().getPrecoDiariaPorTarifa(getTipoTarifa());
	}
	
	public String getValorLocacaoCalculadoDisplay() {
		return NumberFormat.getCurrencyInstance(SystemUtil.LOCALE_BRASIL).format(getValor());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + idLocacao;
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		if (agencia == null) {
			if (other.agencia != null) {
				return false;
			}
		} else if (!agencia.equals(other.agencia)) {
			return false;
		}
		if (cliente == null) {
			if (other.cliente != null) {
				return false;
			}
		} else if (!cliente.equals(other.cliente)) {
			return false;
		}
		if (idLocacao != other.idLocacao) {
			return false;
		}
		if (veiculo == null) {
			if (other.veiculo != null) {
				return false;
			}
		} else if (!veiculo.equals(other.veiculo)) {
			return false;
		}
		return true;
	}
}
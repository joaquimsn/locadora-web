package br.com.jsn.noleggio.domain.model;

import java.io.Serializable;
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

/**
 * The persistent class for the veiculo database table.
 * 
 */
@Entity
@Table(name = "veiculo")
@NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v")
public class Veiculo implements Serializable {
	private static final long serialVersionUID = -7333959167513470776L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_veiculo")
	private int idVeiculo;

	private int ano;

	@Column(name = "ativo", columnDefinition = "BIT", length = 1)
	private boolean ativo;

	private String chassi;

	private String cidade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_manutencao")
	private Date dataManutencao;

	private String fabricante;

	private int grupo;

	@Column(name = "id_funcionario")
	private int idFuncionario;

	private String imagem;

	@Column(name = "km_preco_km_controlado")
	private double kmPrecoKmControlado;

	@Column(name = "km_preco_km_livre")
	private double kmPrecoKmLivre;

	@Column(name = "km_rodado")
	private double kmRodado;

	private String modelo;

	private String placa;

	private int status;

	private String uf;

	// bi-directional many-to-one association to AcessorioVeiculo
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private List<AcessorioVeiculo> listaAcessorioVeiculo;

	// bi-directional many-to-one association to Agencia
	@ManyToOne
	@JoinColumn(name = "id_agencia")
	private Agencia agencia;

	public Veiculo() {
	}

	public int getIdVeiculo() {
		return this.idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getChassi() {
		return this.chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataManutencao() {
		return this.dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getGrupo() {
		return this.grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getIdFuncionario() {
		return this.idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public double getKmPrecoKmControlado() {
		return this.kmPrecoKmControlado;
	}

	public void setKmPrecoKmControlado(double kmPrecoKmControlado) {
		this.kmPrecoKmControlado = kmPrecoKmControlado;
	}

	public double getKmPrecoKmLivre() {
		return this.kmPrecoKmLivre;
	}

	public void setKmPrecoKmLivre(double kmPrecoKmLivre) {
		this.kmPrecoKmLivre = kmPrecoKmLivre;
	}

	public double getKmRodado() {
		return this.kmRodado;
	}

	public void setKmRodado(double kmRodado) {
		this.kmRodado = kmRodado;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<AcessorioVeiculo> getListaAcessorioVeiculos() {
		return this.listaAcessorioVeiculo;
	}

	public void setListaAcessorioVeiculos(List<AcessorioVeiculo> listaAcessorioVeiculos) {
		this.listaAcessorioVeiculo = listaAcessorioVeiculos;
	}

	public AcessorioVeiculo addAcessorioVeiculo(
			AcessorioVeiculo acessorioVeiculo) {
		getListaAcessorioVeiculos().add(acessorioVeiculo);
		acessorioVeiculo.setVeiculo(this);

		return acessorioVeiculo;
	}

	public AcessorioVeiculo removeAcessorioVeiculo(
			AcessorioVeiculo acessorioVeiculo) {
		getListaAcessorioVeiculos().remove(acessorioVeiculo);
		acessorioVeiculo.setVeiculo(null);

		return acessorioVeiculo;
	}

	public Agencia getAgencia() {
		return this.agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVeiculo;
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
		Veiculo other = (Veiculo) obj;
		if (idVeiculo != other.idVeiculo) {
			return false;
		}
		return true;
	}
}
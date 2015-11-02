package br.com.jsn.noleggio.modules.agencia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.jsn.noleggio.main.validation.BusinessValidation;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;


/**
 * The persistent class for the agencia database table.
 */
@Entity
@Table(name="agencia")
@NamedQuery(name="Agencia.findAll", query="SELECT a FROM Agencia a")
public class Agencia extends BusinessValidation implements Serializable {
	private static final long serialVersionUID = 3048129896182422262L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_agencia")
	private int idAgencia;

	@Column(name = "ativo", columnDefinition = "BIT", length = 1)
	private boolean ativo;

	private String bairro;
	
	@NotNull(message = "CEP é Obrigatório")
	private String cep;

	private String cidade;
	
	@CNPJ(message = "CNPJ informado é invalido")
	private String cnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_manutencao")
	private Date dataManutencao;
	
	@Size(min = 4, max = 64,
			message = "Domínio deve conter entre 4 e 80 caracteres")
	private String dominio;
	
	@Email(message = "E-mail informado é invalido")
	private String email;
	
	@Size(min = 4, max = 80,
			message = "Fantasia deve conter entre 4 e 80 caracteres")
	private String fantasia;

	@Column(name="insc_estadual")
	private String inscEstadual;

	private String logradouro;

	private int numero;
	@Size(min = 4, max = 120,
			message = "Razão social deve conter entre 4 e 120 caracteres")
	@Column(name="razao_social")
	private String razaoSocial;

	private String responsavel;

	private String telefone;

	private String uf;

	//bi-directional many-to-one association to Veiculo
	@OneToMany(mappedBy="agencia")
	private List<Veiculo> listaVeiculo;

	public Agencia() {
	}

	public int getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep.replaceAll("[^0-9]", "");
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFantasia() {
		return this.fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getInscEstadual() {
		return this.inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getResponsavel() {
		return this.responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Veiculo> getListaVeiculo() {
		return this.listaVeiculo;
	}

	public void setListaVeiculo(List<Veiculo> listaVeiculo) {
		this.listaVeiculo = listaVeiculo;
	}
	
	public String getStatusAtivoDisplay() {
		if (isAtivo()) {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAgencia;
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
		Agencia other = (Agencia) obj;
		if (idAgencia != other.idAgencia) {
			return false;
		}
		return true;
	}
}
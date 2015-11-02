package br.com.jsn.noleggio.modules.usuario.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jsn.noleggio.modules.funcionario.model.Funcionario;
import br.com.jsn.noleggio.modules.usuario.enums.NivelUsuarioEnum;
import br.com.jsn.noleggio.modules.usuario.enums.StatusAutenticacaoUsuarioEnum;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 3621344325533403812L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "ativo", columnDefinition = "BIT", length = 1)
	private boolean ativo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_manutencao")
	private Date dataManutencao;

	private String dominio;

	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	private int nivel;
	
	@NotNull()
	@Size(min = 6, max = 18, message = "Senha deve conter entre 10 e 50 caracteres")
	private String senha;
	
	@NotNull()
	@Size(min = 4, max = 64, message = "Nome deve conter entre 4 e 64 caracteres")
	@Column(name = "nome_usuario")
	private String nomeUsuario;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		return this.dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public NivelUsuarioEnum getNivel() {
		return NivelUsuarioEnum.getEnumByValue(nivel);
	}

	public void setNivel(NivelUsuarioEnum nivel) {
		this.nivel = nivel.getValue();
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNivelUsuarioDisplay() {
		return NivelUsuarioEnum.getDisplayByValue(nivel);
	}
	
	public NivelUsuarioEnum getNivelUsuarioEnum() {
		return NivelUsuarioEnum.getEnumByValue(nivel);
	}
	
	public StatusAutenticacaoUsuarioEnum getStatusAtenticacao() {
		return StatusAutenticacaoUsuarioEnum.AUTENTICADOR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + idUsuario;
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
		Usuario other = (Usuario) obj;
		if (funcionario == null) {
			if (other.funcionario != null) {
				return false;
			}
		} else if (!funcionario.equals(other.funcionario)) {
			return false;
		}
		if (idUsuario != other.idUsuario) {
			return false;
		}
		return true;
	}
	
}
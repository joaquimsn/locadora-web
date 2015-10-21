package br.com.jsn.noleggio.main;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.funcionario.model.Funcionario;
import br.com.jsn.noleggio.modules.usuario.model.Usuario;
import br.com.jsn.noleggio.modules.usuario.pattern.UsuarioNullObject;

@SessionScoped
public class Session implements Serializable {
	private static final long serialVersionUID = -6739868569207225199L;
	
	private Usuario usuario;

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioNullObject();
		}
		return usuario;
	}

	public Agencia getAgencia() {
		return getFuncionario().getAgencia();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionario() {
		return getUsuario().getFuncionario();
	}
}

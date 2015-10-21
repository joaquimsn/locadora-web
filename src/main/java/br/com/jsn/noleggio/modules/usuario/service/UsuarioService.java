package br.com.jsn.noleggio.modules.usuario.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.jsn.noleggio.main.System;
import br.com.jsn.noleggio.modules.usuario.dao.UsuarioDAO;
import br.com.jsn.noleggio.modules.usuario.enums.StatusAutenticacaoUsuarioEnum;
import br.com.jsn.noleggio.modules.usuario.model.Usuario;

public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 2316629488366168731L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	private Usuario buscarPorCredencial(String nomeUsuario, String dominio, String senha) {
		return usuarioDAO.buscarPorCredencial(nomeUsuario, dominio, senha);
	}

	public boolean efetuarLogin(String login, String senha) {
		Usuario usuario = buscarPorCredencial(getLoginUsuario(login), getDominio(login), senha);
		System.getSession().setUsuario(usuario);
		return usuario.getStatusAtenticacao().equals(StatusAutenticacaoUsuarioEnum.AUTENTICADOR);
	}
	
	private String getDominio(String login) {
		String dominio[];

		dominio = login.split("@");

		if (dominio == null || dominio.length < 2 || dominio[1].equals("")) {
			return null;
		}
		return dominio[1];
	}

	private String getLoginUsuario(String login) {
		return login.split("@")[0];
	}
	
}

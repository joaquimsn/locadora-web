package br.com.jsn.noleggio.modules.usuario.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.jsn.noleggio.modules.usuario.dao.UsuarioDAO;
import br.com.jsn.noleggio.modules.usuario.model.Usuario;

public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 2316629488366168731L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario buscarPorCredencial(String nomeUsuario, String dominio, String senha) {
		return usuarioDAO.buscarPorCredencial(nomeUsuario, dominio, senha);
	}
}

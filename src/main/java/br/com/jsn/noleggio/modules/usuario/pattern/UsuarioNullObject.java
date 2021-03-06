package br.com.jsn.noleggio.modules.usuario.pattern;

import br.com.jsn.noleggio.modules.usuario.enums.StatusAutenticacaoUsuarioEnum;
import br.com.jsn.noleggio.modules.usuario.model.Usuario;

/**
 * Representação default da instacia de um objeto Usuario
 * Pattern NullObject
 * @author Joaquim Neto
 */
public class UsuarioNullObject extends Usuario {
	private static final long serialVersionUID = 1002364820177214238L;

	@Override
	public boolean isAtivo() {
		return false;
	}
	
	@Override
	public StatusAutenticacaoUsuarioEnum getStatusAtenticacao() {
		return StatusAutenticacaoUsuarioEnum.NAO_AUTENTICADO;
	}
}

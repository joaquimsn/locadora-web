package br.com.jsn.noleggio.modules.usuario.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.usuario.model.Usuario;
import br.com.jsn.noleggio.modules.usuario.pattern.UsuarioNullObject;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {
	private static final long serialVersionUID = -6951369622543792299L;

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	/**
	 * Consulta um usuário persistido com base nos parâmetros informados
	 * 
	 * @param nomeUsuario
	 * @param dominio
	 * @param senha
	 * @return Objeto Usuario ou UsuarioNull
	 */
	public Usuario buscarPorCredencial(String nomeUsuario, String dominio, String senha) {
		String jpql = "SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario AND u.dominio = :dominio AND u.senha = :senha";
		
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		query.setParameter("nomeUsuario", nomeUsuario);
		query.setParameter("dominio", dominio);
		query.setParameter("senha", senha);
		
		Usuario usuario;
		
		// Se não encontrar o usuário na base, retorna o usuário default implementando o pattern NullObject
		try {
			usuario = query.getSingleResult();
		} catch (Exception e) {
			usuario = new UsuarioNullObject();
		} 
		
		return usuario;
	}
}

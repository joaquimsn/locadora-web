package br.com.jsn.noleggio.modules.funcionario.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.funcionario.model.Funcionario;
import br.com.jsn.noleggio.modules.usuario.enums.NivelUsuarioEnum;

@Stateless
public class FuncionarioDAO extends GenericDAO<Funcionario> {
	private static final long serialVersionUID = 7604335364254479177L;

	protected FuncionarioDAO() {
		super(Funcionario.class);
	}
	
	public List<Funcionario> findAllSupervisor() {
		String jpql = "SELECT f FROM Funcionario f WHERE f.usuario.nivel = :nivelUsuario";
		
		TypedQuery<Funcionario> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("nivelUsuario", NivelUsuarioEnum.SUPERVISOR.getValue());
		
		return typedQuery.getResultList();
	}
}

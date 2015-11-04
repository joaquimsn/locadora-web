package br.com.jsn.noleggio.modules.locacao.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;

public class LocacaoDAO extends GenericDAO<Locacao> {

	private static final long serialVersionUID = -7779700070920968522L;

	protected LocacaoDAO() {
		super(Locacao.class);
	}

	public List<Locacao> buscarLocacoesPorCpf(String cpf, StatusLocacaoEnum status) {
		String jpql = "SELECT l FROM Locacao f WHERE l.cliente.cpf = :cpf and l.status = :status";
		
		TypedQuery<Locacao> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("cpf", cpf);
		typedQuery.setParameter("status", status.getValue());
		
		return typedQuery.getResultList();
	}

}

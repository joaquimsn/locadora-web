package br.com.jsn.noleggio.modules.locacao.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;

@Stateless
public class LocacaoDAO extends GenericDAO<Locacao> {

	private static final long serialVersionUID = -7779700070920968522L;

	protected LocacaoDAO() {
		super(Locacao.class);
	}

	public List<Locacao> buscarLocacoesPorCpf(String cpf, StatusLocacaoEnum status) {
		String jpql = "SELECT l FROM Locacao l WHERE l.cliente.cpf = :cpf and l.status = :status";
		
		TypedQuery<Locacao> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("cpf", cpf);
		typedQuery.setParameter("status", status.getValue());
		
		return typedQuery.getResultList();
	}
	
	public List<Locacao> buscarLocacoesPorStatus(StatusLocacaoEnum status) {
		String jpql = "SELECT l FROM Locacao l WHERE l.status = :status";
		
		TypedQuery<Locacao> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("status", status.getValue());
		
		return typedQuery.getResultList();
	}

	public List<Locacao> buscarLocacoesPorDataLocacaoMaiorQue(Date dataAtual) {
		String jpql = "SELECT l FROM Locacao l WHERE l.dataLocacao = :data";
		
		TypedQuery<Locacao> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("data", dataAtual);
		
		return typedQuery.getResultList();
	}
 
}

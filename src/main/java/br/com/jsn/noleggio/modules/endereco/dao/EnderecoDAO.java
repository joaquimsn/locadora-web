package br.com.jsn.noleggio.modules.endereco.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;
import br.com.jsn.noleggio.modules.endereco.pattern.EnderecoNullObject;

public class EnderecoDAO extends GenericDAO<Endereco> {
	private static final long serialVersionUID = -8895774017383347155L;

	protected EnderecoDAO() {
		super(Endereco.class);
	}
	
	public Endereco buscarPorCep(String cep) {
		String jpql = "SELECT e FROM Endereco e WHERE e.cep = :cep";
		
		TypedQuery<Endereco> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("cep", cep);
		
		Endereco endereco = null;
		try {
			endereco = typedQuery.getSingleResult();
		} catch (Exception e) {
			endereco = new EnderecoNullObject();
		} 
		
		return endereco;
		
	}
	
	public List<String> buscarCidadePorUf(Uf uf) {
		String jpql = "SELECT c.cidade FROM Cidade c WHERE c.ufBean.uf = :uf";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
		typedQuery.setParameter("uf", uf.getUf());
		
		return typedQuery.getResultList();
	}
	
	public List<Uf> buscarTodasUf() {
		String jpql = "SELECT u FROM Uf u";
		
		TypedQuery<Uf> typedQuery = entityManager.createQuery(jpql, Uf.class);
		
		return typedQuery.getResultList();
	}
}

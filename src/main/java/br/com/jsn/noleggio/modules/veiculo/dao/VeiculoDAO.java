package br.com.jsn.noleggio.modules.veiculo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.veiculo.model.StatusVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;

public class VeiculoDAO extends GenericDAO<Veiculo> {
	private static final long serialVersionUID = 8228557546077794996L;

	public VeiculoDAO() {
		super(Veiculo.class);
	}
	
	public List<Veiculo> findByCity(String nomeCidade) {
		String jpql = "SELECT v FROM Veiculo v WHERE v.cidade = :cidade";
		
		TypedQuery<Veiculo> typedQuery = entityManager.createQuery(jpql, classe);
		
		return typedQuery.getResultList();
	}
	
	public List<Veiculo> findAllByStatus(StatusVeiculoEnum statusVeiculoEnum) {
		String jpql = "SELECT v FROM Veiculo v WHERE v.status = :status";
		
		TypedQuery<Veiculo> typedQuery = entityManager.createQuery(jpql, classe);
		typedQuery.setParameter("status", statusVeiculoEnum.getValue());
		
		return typedQuery.getResultList();
	}
}

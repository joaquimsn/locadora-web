package br.com.jsn.noleggio.main.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = -1346304670895222289L;

	protected Class<T> classe;
	@Inject
	protected EntityManager entityManager;

	protected GenericDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void save(T entity) {
		try {
			entityManager.joinTransaction();
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public T update(T entity) {
		try {
			entityManager.joinTransaction();
			return entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public void delete(Object id, Class<T> classe) {
		try {
			entityManager.joinTransaction();
			T entityToBeRemoved = entityManager.getReference(classe, id);
			entityManager.remove(entityToBeRemoved);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T findById(int id) {
		return entityManager.find(classe, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
		criteriaQuery.select(criteriaQuery.from(classe));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}

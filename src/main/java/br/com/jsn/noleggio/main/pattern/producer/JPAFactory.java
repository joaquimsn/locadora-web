package br.com.jsn.noleggio.main.pattern.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class JPAFactory {
	
	@PersistenceUnit(name = "locadoraweb")
	private EntityManagerFactory entityManagerFactory;
	
	@Produces
	@RequestScoped
	public EntityManager entityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}

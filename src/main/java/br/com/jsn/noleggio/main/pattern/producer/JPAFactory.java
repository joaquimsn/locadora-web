package br.com.jsn.noleggio.main.pattern.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class JPAFactory {
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("locadoraweb");
	}
	
	@Produces
	@Transactional
	@SessionScoped
	public EntityManager entityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager entityManager) {
		entityManager.close();
	}
	
	public void closeEntityManagerFactort(@Disposes EntityManagerFactory entityManagerFactory) {
		entityManagerFactory.close();
	}
}

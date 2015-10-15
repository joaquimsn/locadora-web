package br.com.jsn.noleggio.main.pattern.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("locadoraweb");
	}
	
	@Produces
	@SessionScoped
	public EntityManager entityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager entityManager) {
		entityManager.close();
	}
}

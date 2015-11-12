package br.com.jsn.noleggio.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS provides a deployment agnostic abstract class ApplicationRest for
 * declaring root resource and provider classes.
 * 
 */
@ApplicationPath("/api")
public class ApplicationRest extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public ApplicationRest() {
		singletons.add(new WSController());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
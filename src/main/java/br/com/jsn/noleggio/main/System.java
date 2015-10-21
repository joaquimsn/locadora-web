package br.com.jsn.noleggio.main;

import br.com.jsn.noleggio.main.util.BeanInjector;

public abstract class System {
	
	public static Session getSession() {
		return BeanInjector.getBean(Session.class);
	}
}

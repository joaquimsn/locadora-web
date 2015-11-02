package br.com.jsn.noleggio.main.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import br.com.jsn.noleggio.main.Session;
import br.com.jsn.noleggio.main.System;

public abstract class AbstractBean implements Serializable {
	private static final long serialVersionUID = 4050341785206419827L;
	
	protected boolean readonly;
	protected boolean disabled;
	
	private Session session;
	
	public String abrirPagina() {
		return "";
	}
	
	@PostConstruct
	public void inicializarPagina() {
		session = System.getSession();
	}
	
	public boolean isReadonly() {
		return readonly;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	protected Session getSession() {
		return session;
	}
}

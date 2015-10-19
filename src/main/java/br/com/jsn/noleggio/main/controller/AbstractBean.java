package br.com.jsn.noleggio.main.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public abstract class AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected boolean readonly;
	protected boolean disabled;
	
	public String abrirPagina() {
		return "";
	}
	
	@PostConstruct
	public void inicializarPagina() {
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
}

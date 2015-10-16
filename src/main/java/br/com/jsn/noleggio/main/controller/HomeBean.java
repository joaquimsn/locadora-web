package br.com.jsn.noleggio.main.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.jsn.noleggio.main.security.UrlRoute;

@Named
@ViewScoped
public class HomeBean extends AbstractBean {
	private static final long serialVersionUID = 7739820400705469623L;

	@PostConstruct
	@Override
	public void inicializarPagina() {
		// TODO Auto-generated method stub
		super.inicializarPagina();
	}
	
	@Override
	public String abrirPagina() {
		return UrlRoute.INICIO;
	}

}

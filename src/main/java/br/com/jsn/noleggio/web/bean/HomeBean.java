package br.com.jsn.noleggio.web.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.jsn.noleggio.web.AbstractBean;
import br.com.jsn.noleggio.web.UrlRoute;

@Named
@ViewScoped
public class HomeBean extends AbstractBean {

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

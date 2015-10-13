package br.com.jsn.noleggio.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

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
	
	public static void showMessageInfo(String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", detail));
	}

	public static void showMessageWarn(String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", detail));
	}

	public static void showMessageError(String detail) {
		addBusinessErroCallbackParam();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", detail));
	}
	
	private static void addBusinessErroCallbackParam() {
		RequestContext rc = RequestContext.getCurrentInstance();
		if (rc != null) {
			// Parâmetro businessError usado pelo componente framework:messageHide 
			// também pode ser usado para manter um dialog de erro aberto
			rc.addCallbackParam("businessError", true);
		}
	}
}

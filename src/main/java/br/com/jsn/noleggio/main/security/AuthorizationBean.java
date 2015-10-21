package br.com.jsn.noleggio.main.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.jsn.noleggio.main.Session;
import br.com.jsn.noleggio.main.System;
import br.com.jsn.noleggio.modules.usuario.enums.NivelUsuarioEnum;
import br.com.jsn.noleggio.modules.usuario.enums.StatusAutenticacaoUsuarioEnum;

import com.ocpsoft.pretty.PrettyContext;

@Named
@RequestScoped
public class AuthorizationBean implements Serializable {
	private static final long serialVersionUID = -2087043781097348258L;
	
	private Session session;
	/** Páginas permitidas somente ao usuário Administrador */
	public static final List<String> LISTA_PAGINAS_SOMENTE_ADMINISTRADOR = new ArrayList<String>(Arrays.asList("gerenciamento-agencia, gerenciamento-funcionario, gerenciamento-veiculo, relatorios"));
	
	@PostConstruct
	public void inicializar() {
		session = System.getSession();
	}
	
	public String autorizar() {
		if (!hasAutorizacao(PrettyContext.getCurrentInstance().getRequestURL().toURL().replaceFirst("/", ""))) {
			return UrlRoute.LOGIN;
		}
		return null;
	}

	/**
	 * Redireciona para a tela de início se o usuário estiver logado e tentar acessar a
	 * página de login novamente
	 * @return URL da tela de início se atender às condições
	 */
	public String redirecionar() {
		if (session.getUsuario().getStatusAtenticacao().equals(StatusAutenticacaoUsuarioEnum.AUTENTICADOR) && getRequestURL().contains("/pages/public/login.xhtml")) {
			return UrlRoute.INICIO;
		} else {
			return null;
		}
	}

	/**
	 * Verifica se o usuário está autorizado a acesar a tela
	 * @return <b>true</b> se o usuário estiver autorizado
	 */
	protected boolean hasAutorizacao(String prettyUrl) {
		if (session.getUsuario().getStatusAtenticacao().equals(StatusAutenticacaoUsuarioEnum.NAO_AUTENTICADO)) {
			return false;
		}
		
		if (session.getUsuario().getNivelUsuarioEnum().equals(NivelUsuarioEnum.ATENDENTE) && LISTA_PAGINAS_SOMENTE_ADMINISTRADOR.contains(prettyUrl)) {
			return false;
		}
		
		return true;
	}

	public boolean renderizarItemMenu(String prettyUrl) {
		return hasAutorizacao(prettyUrl);
	}
	
	public String closeSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return UrlRoute.LOGIN;
	}
	
	public static String getRequestURL() {
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();

		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURL().toString();
		} else {
			return "";
		}
	}
}
package br.com.jsn.noleggio.main.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.modules.usuario.service.UsuarioService;
import br.com.jsn.noleggio.teste.ServicoService;

@Named
@ViewScoped
public class LoginBean extends AbstractBean {
	private static final long serialVersionUID = 521790612735303805L;

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ServicoService servicoService;

	private String login;
	private String senha;
	private String mensagemErro;

	@PostConstruct
	public void inicializarPagina() {
	}

	public String efetuarLogin() {
		servicoService.realizarServico();
		
		return UrlRoute.INICIO;
	}
	
	/*
	 * Getters e Setters  
	 */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}

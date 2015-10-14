package br.com.jsn.noleggio.web.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.ClienteService;
import br.com.jsn.noleggio.ServicoService;
import br.com.jsn.noleggio.domain.service.UsuarioService;
import br.com.jsn.noleggio.web.AbstractBean;

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
		Thread trThread = new Thread();
		mensagemErro = login + senha;
		try {
			trThread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
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

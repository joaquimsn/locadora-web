package br.com.jsn.noleggio.main.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;
import br.com.jsn.noleggio.modules.veiculo.service.VeiculoService;

@Named
@ViewScoped
public class HomeBean extends AbstractBean {
	private static final long serialVersionUID = 7739820400705469623L;
	
	@Inject
	private VeiculoService VeiculoService;
	
	private Veiculo objetoSelecionado;
	private List<Veiculo> listaVeiculo;
	private List<Veiculo> listaVeiculoFiltrado;
	
	private String parametroFiltro;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.INICIO;
	}

	@PostConstruct
	@Override
	public void inicializarPagina() {
		objetoSelecionado = new Veiculo();
		
		carregarLista();
	}
	
	public void carregarLista() {
		listaVeiculo = VeiculoService.buscarTodos();
	}
	
	public void filtrarVeiculo() {
		
	}

	public Veiculo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public String getParametroFiltro() {
		return parametroFiltro;
	}

	public void setObjetoSelecionado(Veiculo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public void setParametroFiltro(String parametroFiltro) {
		this.parametroFiltro = parametroFiltro;
	}
	
	public List<Veiculo> getListaTodos() {
		return listaVeiculo;
	}
}

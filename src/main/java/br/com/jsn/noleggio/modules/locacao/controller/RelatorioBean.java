package br.com.jsn.noleggio.modules.locacao.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;
import br.com.jsn.noleggio.modules.locacao.service.LocacaoService;

@Named
@ViewScoped
public class RelatorioBean extends AbstractBean {
	private static final long serialVersionUID = 4706245469364977041L;

	@Inject
	private LocacaoService locacaoService;
	
	private Locacao objetoSelecionado;
	private List<Locacao> listaLocacao;
	private List<Locacao> listaLocacaoFiltrada;
	private Date dataAtual;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.RELATORIO_LOCACOES;
	}

	@Override
	@PostConstruct
	public void inicializarPagina() {
		super.inicializarPagina();
		setReadonly(true);
		setDisabled(true);
		dataAtual = new Date();
		carregarLista();
	}
	
	private void carregarLista() {
		listaLocacao = locacaoService.buscarLocacoesPorDataLocacaoMaiorQue(dataAtual);
	}
	
	public void habilitarEdicao() {
		readonly = false;
		disabled = false;

	}
	
	public Locacao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public List<Locacao> getListaLocacao() {
		return listaLocacao;
	}

	public List<Locacao> getListaLocacaoFiltrada() {
		return listaLocacaoFiltrada;
	}

	public void setListaLocacaoFiltrada(List<Locacao> listaLocacaoFiltrada) {
		this.listaLocacaoFiltrada = listaLocacaoFiltrada;
	}

	public void setObjetoSelecionado(Locacao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
}

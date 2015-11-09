package br.com.jsn.noleggio.modules.locacao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.main.util.DateUtil;
import br.com.jsn.noleggio.main.validation.ValidationModelBusiness;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;
import br.com.jsn.noleggio.modules.locacao.model.Pagamento;
import br.com.jsn.noleggio.modules.locacao.pattern.MultaVO;
import br.com.jsn.noleggio.modules.locacao.service.LocacaoService;
import br.com.jsn.noleggio.modules.veiculo.enums.TipoTarifaEnum;
import br.com.jsn.noleggio.modules.veiculo.model.StatusVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.service.VeiculoService;

@Named
@ViewScoped
public class DevolucaoBean extends AbstractBean {
	private static final long serialVersionUID = 1321633801207113091L;

	@Inject
	private VeiculoService veiculoService;
	@Inject
	private LocacaoService locacaoService;
	
	private Locacao objetoSelecionado;
	private Pagamento pagamentoSelecionado;
	
	private List<Locacao> listaLocacao;
	private List<Locacao> listaLocacaoFiltrada;
	private List<MultaVO> listaMultaVO;
	
	private Date dataAtual;
	private boolean pagamentoAprovado;
	private boolean devolucaoComPendencia;
	private String parametroFiltro;
	
	private double valorAdicional;
	private int kmAtual;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.SERVICO_DEVOLUCAO;
	}

	@Override
	@PostConstruct
	public void inicializarPagina() {
		super.inicializarPagina();
		dataAtual = new Date();
		kmAtual= 0;
		setReadonly(true);
		setDisabled(true);
		valorAdicional = 0;
		objetoSelecionado = new Locacao();
		objetoSelecionado.setTipoTarifa(TipoTarifaEnum.CONTROLADA);
		pagamentoSelecionado = new Pagamento();
		listaMultaVO = new ArrayList<MultaVO>();

		carregarLista();
	}
	
	private void carregarLista() {
		listaLocacao = locacaoService.buscarLocacoesPendentes();
	}
	
	public void filtrarLocacao() {
		listaLocacao = locacaoService.buscarLocacaoAbertasPorCpf(parametroFiltro);

		if (parametroFiltro.isEmpty()) {
			listaLocacao = locacaoService.buscarLocacoesPendentes();
		}
	}
	
	private void preencherCamposLocacao() {
		objetoSelecionado.setStatus(StatusLocacaoEnum.ENCERRADA);
		objetoSelecionado.setDataHoraDevolucao(new Date());
		objetoSelecionado.setValorAcrescimo(getValorAdicional() + getValorMulta()); 
	}

	public void alterar() {
		if (pagamentoAprovado) {
			preencherCamposLocacao();
			locacaoService.alterar(objetoSelecionado);
			
			objetoSelecionado.getVeiculo().setStatus(StatusVeiculoEnum.DISPONIVEL);
			objetoSelecionado.getVeiculo().setKmRodado(kmAtual);
			veiculoService.alterar(objetoSelecionado.getVeiculo());

			ValidationModelBusiness
					.addMessageInfo("Devolução realizada com sucesso");
			inicializarPagina();
		}
	}


	public boolean validar() {
		locacaoService.validar(objetoSelecionado);

		return false;
	}
	
	public void efetuarPagamento() {
		if (!pagamentoAprovado && processarPagamento(pagamentoSelecionado)) {
			pagamentoSelecionado.setValor(objetoSelecionado.getValor());
			pagamentoSelecionado.setDataPagamento(new Date());
			objetoSelecionado.addPagamento(pagamentoSelecionado);
			pagamentoAprovado = true;
			devolucaoComPendencia = false;
			
			ValidationModelBusiness.addMessageInfo("Pagamento realizado com sucesso");
		} else {
			ValidationModelBusiness.addMessageWarn("Pagamento não aprovado pela operadora do cartão");
			pagamentoAprovado = false;
		}
	}
	
	public void habilitarEdicao() {
		readonly = false;
		disabled = false;

	}
	
	private void verificarPendencia() {
		listaMultaVO = new ArrayList<MultaVO>();
		MultaVO multaVO = new MultaVO();
		multaVO.setDescricao("Não possui pendências");
		
		if (objetoSelecionado.getAgenciaDevolucao() != getSession().getAgencia()) {
			// Multa por devolução em local diferente
			multaVO = new MultaVO();
			multaVO.setDescricao("Agência de devolução diferente do registrado");
			multaVO.setPreco(75);
			
			listaMultaVO.add(multaVO);
		}
		
		int dias = DateUtil.intervalInDays(objetoSelecionado.getDataHoraPrevistaDevolucao(), dataAtual); 
		if (dias > 0) {
			multaVO = new MultaVO();
			multaVO.setDescricao("Multa por atraso de " + dias + " dia(s) na devolução");
			multaVO.setPreco(objetoSelecionado.getVeiculo().getPrecoKmLivre() * dias);
			
			listaMultaVO.add(multaVO);
		}
		
		if (listaMultaVO.isEmpty()) {
			listaMultaVO.add(multaVO);
		} else {
			devolucaoComPendencia = true;
		}
	}
	
	/**
	 * Mock 
	 * @param pagamaneto
	 * @return 
	 */
	public boolean processarPagamento(Pagamento pagamento) {
		int numero = (int) (Math.random() * 100);
		
		// Simulaçao tempo de processamento do pagamento
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (numero >= 50) {
			return true;
		}
		
		return false;
	}

	public Locacao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
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
		verificarPendencia();
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public void setListaLocacao(List<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}
	
	public List<MultaVO> getListaMultaVO() {
		return listaMultaVO;
	}

	public String getParametroFiltro() {
		return parametroFiltro;
	}

	public void setParametroFiltro(String parametroFiltro) {
		this.parametroFiltro = parametroFiltro;
	}

	public boolean isDevolucaoComPendencia() {
		return devolucaoComPendencia;
	}
	
	public boolean isPagamentoAprovado() {
		return pagamentoAprovado;
	}

	public double getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}
	
	public double getValorMulta() {
		double valorMulta = 0;
		
		for (MultaVO multaVO : listaMultaVO) {
			valorMulta += multaVO.getPreco();
		}
		
		return valorMulta;
	}
	
	public double getValorTotal() {
		return getValorAdicional() + getValorMulta();
	}

	public int getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}
}

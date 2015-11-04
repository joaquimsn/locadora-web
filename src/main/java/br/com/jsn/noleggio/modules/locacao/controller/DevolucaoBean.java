package br.com.jsn.noleggio.modules.locacao.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.main.validation.ValidationModelBusiness;
import br.com.jsn.noleggio.modules.agencia.service.AgenciaService;
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
	@Inject
	private VeiculoService veiculoService;
	@Inject
	private AgenciaService agenciaService;
	@Inject
	private LocacaoService locacaoService;
	
	private Locacao objetoSelecionado;
	private Pagamento pagamentoSelecionado;
	
	private List<Locacao> listaLocacao;
	private List<Locacao> listaLocacaoFiltrada;
	private List<MultaVO> listaMultaVO;
	
	private Date dataAtual;
	private boolean pagamentoAprovado;
	private String parametroFiltro;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.SERVICO_DEVOLUCAO;
	}

	@Override
	@PostConstruct
	public void inicializarPagina() {
		super.inicializarPagina();
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Locacao();
		objetoSelecionado.setTipoTarifa(TipoTarifaEnum.CONTROLADA);
		pagamentoSelecionado = new Pagamento();

		carregarLista();
	}
	
	private void carregarLista() {
		listaLocacao = locacaoService.buscarTodos();
	}
	
	public void filtrarLocacao() {
		listaLocacao = locacaoService.buscarLocacaoAbertasPorCpf(parametroFiltro);
	}
	
	public void cadastrar() {
		validar();

		if (objetoSelecionado.isValidadoComSucesso()) {
			preencherCamposLocacao();
			locacaoService.alterar(objetoSelecionado);
			
			objetoSelecionado.getVeiculo().setStatus(StatusVeiculoEnum.DISPONIVEL);
			veiculoService.alterar(objetoSelecionado.getVeiculo());

			ValidationModelBusiness
					.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
	}

	private void preencherCamposLocacao() {
		objetoSelecionado.setAgencia(getSession().getAgencia());
		objetoSelecionado.setIdFuncionario(getSession().getFuncionario().getIdFuncionario());
	}

	public void alterar() {
		locacaoService.alterar(objetoSelecionado);

		if (true) {
			ValidationModelBusiness.addMessageInfo("Alterado com sucesso");
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
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public void setListaLocacao(List<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}

	public String getParametroFiltro() {
		return parametroFiltro;
	}

	public void setParametroFiltro(String parametroFiltro) {
		this.parametroFiltro = parametroFiltro;
	}
}

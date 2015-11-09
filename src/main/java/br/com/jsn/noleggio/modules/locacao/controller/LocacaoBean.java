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
import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.agencia.service.AgenciaService;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;
import br.com.jsn.noleggio.modules.cliente.service.ClienteService;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;
import br.com.jsn.noleggio.modules.locacao.model.Pagamento;
import br.com.jsn.noleggio.modules.locacao.service.LocacaoService;
import br.com.jsn.noleggio.modules.veiculo.enums.TipoTarifaEnum;
import br.com.jsn.noleggio.modules.veiculo.model.StatusVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;
import br.com.jsn.noleggio.modules.veiculo.service.VeiculoService;

@Named
@ViewScoped
public class LocacaoBean extends AbstractBean {
	private static final long serialVersionUID = 689944798092935059L;
	
	@Inject
	private VeiculoService veiculoService;
	@Inject
	private ClienteService clienteService;
	@Inject
	private AgenciaService agenciaService;
	@Inject
	private LocacaoService locacaoService;
	
	private Locacao objetoSelecionado;
	private Pagamento pagamentoSelecionado;
	private Cliente clienteSelecionado;
	private Agencia agenciaDevolucaoSelecionada;
	
	private List<Veiculo> listaVeiculo;
	private List<Cliente> listaCliente;
	private List<Agencia> listaAgencia;
	
	private Date dataAtual;
	private boolean pagamentoAprovado;
	private boolean locacaoRealizada;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.SERVICO_LOCACAO;
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
		locacaoRealizada = false;

		carregarLista();
	}

	private void carregarLista() {
		listaVeiculo = veiculoService.buscarTodosDisponivelParaLocacao();
		listaCliente = clienteService.buscarTodos();
		listaAgencia = agenciaService.buscarTodos();
	}

	public void cadastrar() {
		validar();

		if (isPagamentoAprovado()) {
			preencherCamposLocacao();
			locacaoService.salvar(objetoSelecionado);
			
			objetoSelecionado.getVeiculo().setStatus(StatusVeiculoEnum.LOCADO);
			veiculoService.alterar(objetoSelecionado.getVeiculo());
			locacaoRealizada = true;
			
			ValidationModelBusiness
					.addMessageInfo("Locação realizada com sucesso");
		}
	}

	private void preencherCamposLocacao() {
		objetoSelecionado.setIdAgencia(getSession().getAgencia().getIdAgencia());
		objetoSelecionado.setIdAgenciaDevolucao(getAgenciaDevolucaoSelecionada().getIdAgencia());
		objetoSelecionado.setCliente(clienteSelecionado);
		objetoSelecionado.setIdFuncionario(getSession().getFuncionario().getIdFuncionario());
		objetoSelecionado.setStatus(StatusLocacaoEnum.ABERTA);
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

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	public Agencia getAgenciaDevolucaoSelecionada() {
		return agenciaDevolucaoSelecionada;
	}

	public void setAgenciaDevolucaoSelecionada(
			Agencia agenciaDevolucaoSelecionada) {
		this.agenciaDevolucaoSelecionada = agenciaDevolucaoSelecionada;
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public List<Veiculo> getListaVeiculo() {
		return listaVeiculo;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setObjetoSelecionado(Locacao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public List<Agencia> getListaAgencia() {
		return listaAgencia;
	}
	
	public List<TipoTarifaEnum> getListaTipoTarifaEnum() {
		return TipoTarifaEnum.getEnumList();
	}
	
	public boolean isLocacaoRealizada() {
		return locacaoRealizada;
	}

	public Date getDataAtual() {
		if (dataAtual == null) {
			dataAtual = new Date();
		}
		return dataAtual;
	}

	public boolean isPagamentoAprovado() {
		return pagamentoAprovado;
	}
}

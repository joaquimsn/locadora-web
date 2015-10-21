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
import br.com.jsn.noleggio.modules.locacao.model.Locacao;
import br.com.jsn.noleggio.modules.locacao.model.Pagamento;
import br.com.jsn.noleggio.modules.locacao.service.LocacaoService;
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
	private Veiculo veiculoSelecionado;
	private Cliente clienteSelecionado;
	private Agencia agenciaDevolucao;
	
	private List<Veiculo> listaVeiculo;
	private List<Cliente> listaCliente;
	private List<Agencia> listaAgencia;
	
	private Date dataAtual;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.SERVICO_LOCACAO;
	}

	@Override
	@PostConstruct
	public void inicializarPagina() {
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Locacao();
		pagamentoSelecionado = new Pagamento();

		carregarLista();
	}

	private void carregarLista() {
		listaVeiculo = veiculoService.buscarTodos();
		listaCliente = clienteService.buscarTodos();
		listaAgencia = agenciaService.buscarTodos();
	}

	public void cadastrar() {
		validar();

		if (objetoSelecionado.isValidadoComSucesso()) {
			locacaoService.salvar(objetoSelecionado);

			ValidationModelBusiness
					.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
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

	public void habilitarEdicao() {
		readonly = false;
		disabled = false;

	}

	public Locacao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public Agencia getAgenciaDevolucao() {
		return agenciaDevolucao;
	}

	public void setAgenciaDevolucao(Agencia agenciaDevolucao) {
		this.agenciaDevolucao = agenciaDevolucao;
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

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public List<Agencia> getListaAgencia() {
		return listaAgencia;
	}
	
	public Date getDataAtual() {
		if (dataAtual == null) {
			dataAtual = new Date();
		}
		return dataAtual;
	}
}

package br.com.jsn.noleggio.modules.locacao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.main.validation.ValidationModelBusiness;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;
import br.com.jsn.noleggio.modules.cliente.service.ClienteService;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;
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
	private LocacaoService locacaoService;
	
	private Locacao objetoSelecionado;
	private Veiculo veiculoSelecionado;
	private Cliente clienteSelecionado;
	
	private List<Veiculo> listaVeiculo;
	private List<Cliente> listaCliente;
	
	private Agencia agenciaDevolucao;
	
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

		carregarLista();
	}

	private void carregarLista() {
		listaVeiculo = veiculoService.buscarTodos();
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
}

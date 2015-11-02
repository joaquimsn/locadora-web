package br.com.jsn.noleggio.modules.usuario.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.controller.ICrudBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.main.util.DateUtil;
import br.com.jsn.noleggio.main.validation.ValidationModelBusiness;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;
import br.com.jsn.noleggio.modules.cliente.service.ClienteService;
import br.com.jsn.noleggio.modules.endereco.BuscarCidades;
import br.com.jsn.noleggio.modules.endereco.EnderecoUf;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;
import br.com.jsn.noleggio.modules.endereco.service.EnderecoService;

@Named
@ViewScoped
public class ClienteBean extends AbstractBean implements ICrudBean<Cliente> {
	private static final long serialVersionUID = 7727135261960352762L;

	@Inject
	@BuscarCidades
	private Event<Uf> eventEndereco;
	@Inject
	private ClienteService clienteService;
	@Inject
	private EnderecoService enderecoService;
	@Inject
	@EnderecoUf
	private List<Uf> listaUf;
	
	private Cliente objetoSelecionado;
	private List<Cliente> listaCliente;
	private List<Cliente> listaClienteFiltrado;
	
	private Uf uf;
	private List<String> listaCidade;
	private Endereco endereco;
	
	private Date dataAtual;
	private Date dataNascimentoMax;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.GERENCIAMENTO_CLIENTE;
	}
		
	@Override
	@PostConstruct
	public void inicializarPagina() {
		super.inicializarPagina();
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Cliente();
		
		dataAtual = new Date();
		
		carregarLista();
	}
	
	private void carregarLista() {
		listaCliente = clienteService.buscarTodos();
		listaClienteFiltrado = null;
	}
	
	@Override
	public void cadastrar() {
		validar();
		
		if (objetoSelecionado.isValidadoComSucesso()) {
			objetoSelecionado.setAgencia(getSession().getAgencia());
			objetoSelecionado.setIdFuncionario(getSession().getFuncionario().getIdFuncionario());
			clienteService.salvar(objetoSelecionado);
			
			ValidationModelBusiness.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
	}

	@Override
	public void alterar() {
		clienteService.alterar(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Alterado com sucesso");
		}
	}

	@Override
	public void excluir() {
		clienteService.excluir(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Inativado com sucesso");
		}
		
	}

	@Override
	public boolean validar() {
		clienteService.validar(objetoSelecionado);
		
		return false;
	}

	@Override
	public void habilitarEdicao() {
		readonly = false;
		disabled = false;
		
	}
	
	public void carregarCidades() {
		eventEndereco.fire(uf);
		listaCidade = uf.getListaCidadeString();
		
		objetoSelecionado.setUf(uf.getUf());
	}
	
	public void buscarEndereco() {
		if (endereco == null || !objetoSelecionado.getCep().equals(endereco.getCep())) {
			endereco = enderecoService.buscarEnderecoPorUf(objetoSelecionado.getCep());
			objetoSelecionado.setUf(endereco.getCidade().getUfBean().getUf());
			objetoSelecionado.setLogradouro(endereco.getEndereco());
			objetoSelecionado.setBairro(endereco.getBairro().getBairro());
			objetoSelecionado.setCidade(endereco.getCidade().getCidade());
			
			uf = endereco.getCidade().getUfBean();
			carregarCidades();
		}
	}

	@Override
	public List<Cliente> getListaTodos() {
		return listaCliente;
	}

	@Override
	public void setListaTodos(List<Cliente> lista) {
		listaCliente = lista;
	}

	@Override
	public Cliente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	public void setObjetoSelecionado(Cliente objeto) {
		if (objeto != null) {
			objetoSelecionado = objeto;
			setDisabled(false);
			
			buscarEndereco();
		} else {
			setDisabled(true);
		}
	}

	@Override
	public List<Cliente> getListaFiltrada() {
		return listaClienteFiltrado;
	}

	@Override
	public void setListaFiltrada(List<Cliente> lista) {
		listaClienteFiltrado = lista;
	}
	
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		if (uf != null) {
			this.uf = uf;
		}
	}

	public List<Uf> getListaUf() {
		return listaUf;
	}

	public List<String> getListaCidade() {
		return listaCidade;
	}

	public Date getDataAtual() {
		return dataAtual;
	}
	
	public Date getDataNascimentoMax() {
		if (dataNascimentoMax == null) {
			dataNascimentoMax = DateUtil.asDate(DateUtil.pastDateFromCurrent(18));
		}
		
		return dataNascimentoMax;
	}
}

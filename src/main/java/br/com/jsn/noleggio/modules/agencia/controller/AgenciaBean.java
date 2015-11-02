package br.com.jsn.noleggio.modules.agencia.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.controller.ICrudBean;
import br.com.jsn.noleggio.main.security.UrlRoute;
import br.com.jsn.noleggio.main.validation.ValidationModelBusiness;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.agencia.service.AgenciaService;
import br.com.jsn.noleggio.modules.endereco.BuscarCidades;
import br.com.jsn.noleggio.modules.endereco.EnderecoUf;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;
import br.com.jsn.noleggio.modules.endereco.service.EnderecoService;

@Named
@ViewScoped
public class AgenciaBean extends AbstractBean implements ICrudBean<Agencia> {
	private static final long serialVersionUID = -2157980839003631019L;
	
	@Inject
	@BuscarCidades
	private Event<Uf> eventEndereco;
	@Inject
	private AgenciaService agenciaService;
	@Inject
	private EnderecoService enderecoService;
	@Inject
	@EnderecoUf
	private List<Uf> listaUf;
	
	private Agencia objetoSelecionado;
	private List<Agencia> listaAgencia;
	private List<Agencia> listaAgenciaFiltrado;
	
	private Uf uf;
	private List<String> listaCidade;
	private Endereco endereco;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.GERENCIAMENTO_AGENCIA;
	}
		
	@Override
	@PostConstruct
	public void inicializarPagina() {
		super.inicializarPagina();
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Agencia();
		
		carregarLista();
	}
	
	private void carregarLista() {
		listaAgencia = agenciaService.buscarTodos();
		listaAgenciaFiltrado = null;
	}
	
	@Override
	public void cadastrar() {
		validar();
		
		if (objetoSelecionado.isValidadoComSucesso()) {
			agenciaService.salvar(objetoSelecionado);
			
			ValidationModelBusiness.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
	}

	@Override
	public void alterar() {
		agenciaService.alterar(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Alterado com sucesso");
		}
	}

	@Override
	public void excluir() {
		agenciaService.excluir(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Inativado com sucesso");
		}
		
	}

	@Override
	public boolean validar() {
		agenciaService.validar(objetoSelecionado);
		
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
	public List<Agencia> getListaTodos() {
		return listaAgencia;
	}

	@Override
	public void setListaTodos(List<Agencia> lista) {
		listaAgencia = lista;
	}

	@Override
	public Agencia getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	public void setObjetoSelecionado(Agencia objeto) {
		if (objeto != null) {
			objetoSelecionado = objeto;
			setDisabled(false);
			
			buscarEndereco();
		} else {
			setDisabled(true);
		}
	}

	@Override
	public List<Agencia> getListaFiltrada() {
		return listaAgenciaFiltrado;
	}

	@Override
	public void setListaFiltrada(List<Agencia> lista) {
		listaAgenciaFiltrado = lista;
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
}

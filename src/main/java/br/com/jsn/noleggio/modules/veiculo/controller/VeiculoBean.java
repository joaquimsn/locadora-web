package br.com.jsn.noleggio.modules.veiculo.controller;

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
import br.com.jsn.noleggio.modules.endereco.BuscarCidades;
import br.com.jsn.noleggio.modules.endereco.EnderecoUf;
import br.com.jsn.noleggio.modules.endereco.model.Uf;
import br.com.jsn.noleggio.modules.veiculo.enums.AcessorioVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.enums.GrupoVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;
import br.com.jsn.noleggio.modules.veiculo.service.VeiculoService;

@Named
@ViewScoped
public class VeiculoBean extends AbstractBean implements ICrudBean<Veiculo> {
	private static final long serialVersionUID = -3542261911323550620L;
	
	@Inject
	@BuscarCidades
	Event<Uf> eventEndereco;
	@Inject
	private VeiculoService veiculoService;
	@Inject
	@EnderecoUf
	private List<Uf> listaUf;

	private Veiculo objetoSelecionado;
	private List<Veiculo> listaVeiculo;
	private List<Veiculo> listaVeiculoFiltrado;

	private Uf uf;
	private List<String> listaCidade;
	
	private List<AcessorioVeiculoEnum> listaAcessorioEnum;
	private List<GrupoVeiculoEnum> listaGrupoEnum;
	private List<AcessorioVeiculoEnum> listaAcessorioEnumSelecionado;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.GERENCIAMENTO_VEICULO;
	}

	@Override
	@PostConstruct
	public void inicializarPagina() {
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Veiculo();

		carregarLista();
	}

	private void carregarLista() {
		listaVeiculo = veiculoService.buscarTodos();
		listaAcessorioEnum = AcessorioVeiculoEnum.getEnumList();
		listaGrupoEnum = GrupoVeiculoEnum.getEnumList();
		
		listaVeiculoFiltrado = null;
		listaAcessorioEnumSelecionado = null;
	}

	@Override
	public void cadastrar() {
		validar();

		if (objetoSelecionado.isValidadoComSucesso()) {
			veiculoService.salvar(objetoSelecionado);

			ValidationModelBusiness
					.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
	}

	@Override
	public void alterar() {
		veiculoService.alterar(objetoSelecionado);

		if (true) {
			ValidationModelBusiness.addMessageInfo("Alterado com sucesso");
		}
	}

	@Override
	public void excluir() {
		veiculoService.excluir(objetoSelecionado);

		if (true) {
			ValidationModelBusiness.addMessageInfo("Inativado com sucesso");
		}

	}

	@Override
	public boolean validar() {
		veiculoService.validar(objetoSelecionado);

		return false;
	}

	@Override
	public void habilitarEdicao() {
		readonly = false;
		disabled = false;

	}

	public void carregarCidades() {
		uf = new Uf();
		uf.setUf(objetoSelecionado.getUf());
		
		eventEndereco.fire(uf);
		listaCidade = uf.getListaCidadeString();

		objetoSelecionado.setUf(uf.getUf());
	}


	@Override
	public List<Veiculo> getListaTodos() {
		return listaVeiculo;
	}

	@Override
	public void setListaTodos(List<Veiculo> lista) {
		listaVeiculo = lista;
	}

	@Override
	public Veiculo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	public void setObjetoSelecionado(Veiculo objeto) {
		if (objeto != null) {
			objetoSelecionado = objeto;
			setDisabled(false);
			
			carregarCidades();
		} else {
			setDisabled(true);
		}
	}

	@Override
	public List<Veiculo> getListaFiltrada() {
		return listaVeiculoFiltrado;
	}

	@Override
	public void setListaFiltrada(List<Veiculo> lista) {
		listaVeiculoFiltrado = lista;
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

	public List<AcessorioVeiculoEnum> getListaAcessorioEnum() {
		return listaAcessorioEnum;
	}

	public List<GrupoVeiculoEnum> getListaGrupoEnum() {
		return listaGrupoEnum;
	}

	public List<AcessorioVeiculoEnum> getListaAcessorioEnumSelecionado() {
		return listaAcessorioEnumSelecionado;
	}

	public void setListaAcessorioEnumSelecionado(
			List<AcessorioVeiculoEnum> listaAcessorioEnumSelecionado) {
		this.listaAcessorioEnumSelecionado = listaAcessorioEnumSelecionado;
	}
}

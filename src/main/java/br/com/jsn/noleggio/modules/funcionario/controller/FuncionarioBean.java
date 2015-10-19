package br.com.jsn.noleggio.modules.funcionario.controller;

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
import br.com.jsn.noleggio.modules.endereco.BuscarCidades;
import br.com.jsn.noleggio.modules.endereco.EnderecoUf;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;
import br.com.jsn.noleggio.modules.endereco.service.EnderecoService;
import br.com.jsn.noleggio.modules.funcionario.model.Funcionario;
import br.com.jsn.noleggio.modules.funcionario.service.FuncionarioService;
import br.com.jsn.noleggio.modules.usuario.enums.NivelUsuarioEnum;

@Named
@ViewScoped
public class FuncionarioBean extends AbstractBean implements ICrudBean<Funcionario> {
	private static final long serialVersionUID = 7740968948666965866L;
	
	@Inject
	@BuscarCidades
	Event<Uf> eventEndereco;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private EnderecoService enderecoService;
	@Inject
	@EnderecoUf
	private List<Uf> listaUf;
	
	private Funcionario objetoSelecionado;
	private List<Funcionario> listaFuncionario;
	private List<Funcionario> listaFuncionarioFiltrado;
	private List<Funcionario> listaFuncionarioSupervisor;
	
	private Uf uf;
	private List<String> listaCidade;
	private Endereco endereco;
	
	private List<NivelUsuarioEnum> listaNivelUsuarioEnum;
	
	private Date dataAtual;
	private Date dataNascimentoMax;
	
	@Override
	public String abrirPagina() {
		return UrlRoute.GERENCIAMENTO_FUNCIONARIO;
	}
		
	@Override
	@PostConstruct
	public void inicializarPagina() {
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Funcionario();
		
		dataAtual = new Date();
		
		carregarLista();
	}
	
	private void carregarLista() {
		listaFuncionario = funcionarioService.buscarTodos();
		listaFuncionarioSupervisor = funcionarioService.buscarTodosSupervisor();
		listaFuncionarioFiltrado = null;
		listaNivelUsuarioEnum = NivelUsuarioEnum.getEnumList();
	}
	
	@Override
	public void cadastrar() {
		validar();
		
		if (objetoSelecionado.isValidadoComSucesso()) {
			funcionarioService.salvar(objetoSelecionado);
			
			ValidationModelBusiness.addMessageInfo("Cadastro realizado com sucesso");
			inicializarPagina();
		}
	}

	@Override
	public void alterar() {
		funcionarioService.alterar(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Alterado com sucesso");
		}
	}

	@Override
	public void excluir() {
		funcionarioService.excluir(objetoSelecionado);
		
		if (true) {
			ValidationModelBusiness.addMessageInfo("Inativado com sucesso");
		}
		
	}

	@Override
	public boolean validar() {
		funcionarioService.validar(objetoSelecionado);
		
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
	public List<Funcionario> getListaTodos() {
		return listaFuncionario;
	}

	@Override
	public void setListaTodos(List<Funcionario> lista) {
		listaFuncionario = lista;
	}

	@Override
	public Funcionario getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	public void setObjetoSelecionado(Funcionario objeto) {
		if (objeto != null) {
			objetoSelecionado = objeto;
			setDisabled(false);
			
			buscarEndereco();
		} else {
			setDisabled(true);
		}
	}

	@Override
	public List<Funcionario> getListaFiltrada() {
		return listaFuncionarioFiltrado;
	}
	
	public List<Funcionario> getListaFuncionarioSupervisor() {
		return listaFuncionarioSupervisor;
	}

	@Override
	public void setListaFiltrada(List<Funcionario> lista) {
		listaFuncionarioFiltrado = lista;
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
	
	public List<NivelUsuarioEnum> getListaNivelUsuarioEnum() {
		return listaNivelUsuarioEnum;
	}
}

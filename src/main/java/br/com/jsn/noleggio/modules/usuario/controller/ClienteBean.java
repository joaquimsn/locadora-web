package br.com.jsn.noleggio.modules.usuario.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsn.noleggio.main.controller.AbstractBean;
import br.com.jsn.noleggio.main.controller.ICrudBean;
import br.com.jsn.noleggio.main.util.DateUtil;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;
import br.com.jsn.noleggio.modules.cliente.service.ClienteService;

@Named
@ViewScoped
public class ClienteBean extends AbstractBean implements ICrudBean<Cliente> {

	@Inject
	private ClienteService clienteService;
	
	private Cliente objetoSelecionado;
	private List<Cliente> listaCliente;
	private List<Cliente> listaClienteFiltrado;
	
	private Date dataAtual;
	private Date dataNascimentoMax;
	
	@Override
	@PostConstruct
	public void inicializarPagina() {
		setReadonly(true);
		setDisabled(true);
		objetoSelecionado = new Cliente();
		
		dataAtual = new Date();
		
		carregarLista();
	}
	
	private void carregarLista() {
		listaCliente = clienteService.buscarTodos();
	}
	
	@Override
	public void cadastrar() {
		clienteService.validar(objetoSelecionado);
	}

	@Override
	public void alterar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validar() {
		return false;
	}

	@Override
	public void habilitarEdicao() {
		readonly = false;
		disabled = false;
		
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

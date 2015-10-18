package br.com.jsn.noleggio.modules.cliente.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.jsn.noleggio.main.qualifier.SavePreValidate;
import br.com.jsn.noleggio.main.qualifier.UpdatePreValidate;
import br.com.jsn.noleggio.modules.cliente.dao.ClienteDAO;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;

public class ClienteService implements Serializable {
	private static final long serialVersionUID = 3057868317207496193L;
	
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	@SavePreValidate
	@UpdatePreValidate
	private Event<Cliente> eventCliente;
	
	public void salvar(Cliente cliente) {
		clienteDAO.save(cliente);
	}
	
	public void alterar(Cliente cliente) {
		cliente.setDataManutencao(new Date());
		clienteDAO.update(cliente);
	}
	
	@SuppressWarnings("unchecked")
	public void excluir(Cliente cliente) {
		Class<Cliente> clienteClass = (Class<Cliente>) cliente.getClass();
		clienteDAO.delete(cliente.getIdCliente(), clienteClass);
	}
	
	public List<Cliente> buscarTodos() {
		return clienteDAO.findAll();
	}
	
	public void validar(Cliente cliente) {
		eventCliente.fire(cliente);
	}
}

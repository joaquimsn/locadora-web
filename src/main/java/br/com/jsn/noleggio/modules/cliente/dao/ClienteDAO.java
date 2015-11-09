package br.com.jsn.noleggio.modules.cliente.dao;

import javax.ejb.Stateless;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.cliente.model.Cliente;

@Stateless
public class ClienteDAO extends GenericDAO<Cliente>{
	private static final long serialVersionUID = -2998567498054401756L;

	public ClienteDAO() {
		super(Cliente.class);
	}
}

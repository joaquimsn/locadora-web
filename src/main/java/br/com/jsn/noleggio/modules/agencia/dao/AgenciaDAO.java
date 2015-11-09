package br.com.jsn.noleggio.modules.agencia.dao;

import javax.ejb.Stateless;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;

@Stateless
public class AgenciaDAO extends GenericDAO<Agencia> {
	private static final long serialVersionUID = 170123473333182840L;

	public AgenciaDAO() {
		super(Agencia.class);
	}
}

package br.com.jsn.noleggio.modules.locacao.dao;

import br.com.jsn.noleggio.main.dao.GenericDAO;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;

public class LocacaoDAO extends GenericDAO<Locacao> {

	private static final long serialVersionUID = -7779700070920968522L;

	protected LocacaoDAO() {
		super(Locacao.class);
	}

}

package br.com.jsn.noleggio.modules.locacao.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.jsn.noleggio.main.qualifier.SavePreValidate;
import br.com.jsn.noleggio.main.qualifier.UpdatePreValidate;
import br.com.jsn.noleggio.modules.locacao.dao.LocacaoDAO;
import br.com.jsn.noleggio.modules.locacao.enums.StatusLocacaoEnum;
import br.com.jsn.noleggio.modules.locacao.model.Locacao;

public class LocacaoService implements Serializable {
	private static final long serialVersionUID = -2259849718779513514L;
	
	@Inject
	private LocacaoDAO locacaoDAO;
	@Inject
	@SavePreValidate
	@UpdatePreValidate
	private Event<Locacao> eventCliente;
	
	public void salvar(Locacao locacao) {
		locacaoDAO.save(locacao);
	}
	
	public void alterar(Locacao locacao) {
		locacaoDAO.update(locacao);
	}
	
	public List<Locacao> buscarTodos() {
		return locacaoDAO.findAll();
	}
	
	public List<Locacao> buscarLocacaoAbertasPorCpf(String cpf) {
		return locacaoDAO.buscarLocacoesPorCpf(cpf, StatusLocacaoEnum.ABERTA);
	}
	
	public void validar(Locacao locacao) {
		eventCliente.fire(locacao);
	}

}

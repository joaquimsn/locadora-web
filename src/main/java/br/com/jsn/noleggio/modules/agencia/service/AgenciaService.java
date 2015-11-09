package br.com.jsn.noleggio.modules.agencia.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.jsn.noleggio.main.qualifier.SavePreValidate;
import br.com.jsn.noleggio.modules.agencia.dao.AgenciaDAO;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;

public class AgenciaService implements Serializable {
	private static final long serialVersionUID = 2805354980228507959L;

	@Inject
	@SavePreValidate
	private Event<Agencia> eventAgencia;
	@Inject
	private AgenciaDAO agenciaDAO;

	public void salvar(Agencia agencia) {
		agencia.setDataCadastro(new Date());
		agencia.setDataManutencao(new Date());
		agencia.setAtivo(true);
		agenciaDAO.save(agencia);
	}

	public void alterar(Agencia agencia) {
		agencia.setDataManutencao(new Date());
		agenciaDAO.update(agencia);
	}

	public void inativar(Agencia agencia) {
		agencia.setAtivo(false);
		agencia.setDataManutencao(new Date());
		agenciaDAO.update(agencia);
	}

	@SuppressWarnings("unchecked")
	public void excluir(Agencia agencia) {
		Class<Agencia> agenciaClass = (Class<Agencia>) agencia.getClass();
		agenciaDAO.delete(agencia.getIdAgencia(), agenciaClass);
	}

	public Agencia buscarPorId(int idAgenciaDevolucao) {
		return agenciaDAO.findById(idAgenciaDevolucao);
	}

	public List<Agencia> buscarTodos() {
		return agenciaDAO.findAll();
	}
	
	public void validar(Agencia agencia) {
		eventAgencia.fire(agencia);
	}
}

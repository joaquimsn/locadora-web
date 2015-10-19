package br.com.jsn.noleggio.modules.funcionario.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.jsn.noleggio.main.qualifier.SavePreValidate;
import br.com.jsn.noleggio.modules.funcionario.dao.FuncionarioDAO;
import br.com.jsn.noleggio.modules.funcionario.model.Funcionario;

public class FuncionarioService implements Serializable {
	private static final long serialVersionUID = 4432247037054224093L;

	@Inject
	@SavePreValidate
	private Event<Funcionario> eventFuncionario;
	@Inject
	private FuncionarioDAO funcionarioDAO;

	public void salvar(Funcionario funcionario) {
		funcionario.setDataManutencao(new Date());
		funcionario.setAtivo(true);
		funcionarioDAO.save(funcionario);
	}

	public void alterar(Funcionario funcionario) {
		funcionario.setDataManutencao(new Date());
		funcionarioDAO.update(funcionario);
	}

	public void inativar(Funcionario funcionario) {
		funcionario.setAtivo(false);
		funcionario.setDataManutencao(new Date());
		funcionarioDAO.update(funcionario);
	}

	@SuppressWarnings("unchecked")
	public void excluir(Funcionario funcionario) {
		Class<Funcionario> funcionarioClass = (Class<Funcionario>) funcionario.getClass();
		funcionarioDAO.delete(funcionario.getIdFuncionario(), funcionarioClass);
	}

	public List<Funcionario> buscarTodos() {
		return funcionarioDAO.findAll();
	}
	
	public List<Funcionario> buscarTodosSupervisor() {
		return funcionarioDAO.findAllSupervisor();
	}

	public void validar(Funcionario funcionario) {
		eventFuncionario.fire(funcionario);
	}
}

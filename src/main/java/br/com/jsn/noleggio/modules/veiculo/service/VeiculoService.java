package br.com.jsn.noleggio.modules.veiculo.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.jsn.noleggio.main.qualifier.SavePreValidate;
import br.com.jsn.noleggio.main.qualifier.UpdatePreValidate;
import br.com.jsn.noleggio.modules.veiculo.dao.VeiculoDAO;
import br.com.jsn.noleggio.modules.veiculo.model.StatusVeiculoEnum;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;

public class VeiculoService implements Serializable {
	private static final long serialVersionUID = -5668431732326569805L;
	
	@Inject
	private VeiculoDAO veiculoDAO;
	@Inject
	@SavePreValidate
	@UpdatePreValidate
	private Event<Veiculo> eventVeiculo;

	public void salvar(Veiculo veiculo) {
		veiculo.setDataCadastro(new Date());
		veiculo.setDataManutencao(new Date());
		veiculo.setAtivo(true);
		veiculo.setStatus(StatusVeiculoEnum.DISPONIVEL);
		veiculoDAO.save(veiculo);
	}

	public void alterar(Veiculo veiculo) {
		veiculo.setDataManutencao(new Date());
		veiculoDAO.update(veiculo);
	}

	public void inativar(Veiculo veiculo) {
		veiculo.setAtivo(false);
		veiculo.setDataManutencao(new Date());
		veiculoDAO.update(veiculo);
	}

	@SuppressWarnings("unchecked")
	public void excluir(Veiculo veiculo) {
		Class<Veiculo> veiculoClass = (Class<Veiculo>) veiculo.getClass();
		veiculoDAO.delete(veiculo.getIdVeiculo(), veiculoClass);
	}

	public List<Veiculo> buscarTodos() {
		return veiculoDAO.findAll();
	}

	public List<Veiculo> buscarVeiculosPorCidade(String nomeCidade) {
		return veiculoDAO.findByCity(nomeCidade);
	}

	public void validar(Veiculo veiculo) {
		eventVeiculo.fire(veiculo);
	}
}

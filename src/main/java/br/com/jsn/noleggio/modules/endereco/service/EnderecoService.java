package br.com.jsn.noleggio.modules.endereco.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.jsn.noleggio.modules.endereco.BuscarCidades;
import br.com.jsn.noleggio.modules.endereco.EnderecoUf;
import br.com.jsn.noleggio.modules.endereco.dao.EnderecoDAO;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;

public class EnderecoService implements Serializable {
	private static final long serialVersionUID = 4865928499596501626L;

	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void preencherCidadesStringDaUf(@Observes @BuscarCidades Uf uf) {
		uf.setListaCidadeString(enderecoDAO.buscarCidadePorUf(uf));
	}
	
	public List<Uf> buscarTodasUf() {
		return enderecoDAO.buscarTodasUf();
	}
	
	public Endereco buscarEnderecoPorUf(String cep) {
		return enderecoDAO.buscarPorCep(cep);
	}
	
	@Produces
	@EnderecoUf
	@SessionScoped
	public List<Uf> producerListUf() {
		return enderecoDAO.buscarTodasUf();
	}
}

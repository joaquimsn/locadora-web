package br.com.jsn.noleggio;

import java.io.Serializable;

import javax.enterprise.event.Observes;

import br.com.jsn.noleggio.domain.model.Cliente;

public class ClienteService implements Serializable {
	
	private static final long serialVersionUID = 1002430917712750773L;

	public void atriburClienteAoServico(@Observes Servico servico) {
		servico.setCliente(new Cliente());
	}

}

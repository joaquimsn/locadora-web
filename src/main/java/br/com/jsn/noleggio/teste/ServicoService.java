package br.com.jsn.noleggio.teste;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class ServicoService implements Serializable {
	private static final long serialVersionUID = -2431570608512519038L;
	
	@Inject
	private Event<Servico> evento;
		
	public void realizarServico() {
		Servico servico = new Servico();
		evento.fire(servico);
		System.out.println(servico.getCliente());
	}
}

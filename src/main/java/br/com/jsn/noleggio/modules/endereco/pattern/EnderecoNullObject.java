package br.com.jsn.noleggio.modules.endereco.pattern;

import br.com.jsn.noleggio.modules.endereco.model.Bairro;
import br.com.jsn.noleggio.modules.endereco.model.Cidade;
import br.com.jsn.noleggio.modules.endereco.model.Endereco;
import br.com.jsn.noleggio.modules.endereco.model.Uf;

public class EnderecoNullObject extends Endereco {
	private static final long serialVersionUID = -5223557200640980846L;
	
	public EnderecoNullObject() {
		inicializarCidade();
		inicializarBairro();
	}
	
	private void inicializarCidade() {
		Cidade cidade = new Cidade();
		cidade.setUfBean(new  Uf());
		setCidade(cidade);
	}
	
	private void inicializarBairro() {
		setBairro(new Bairro());
	}
}

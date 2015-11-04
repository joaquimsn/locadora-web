package br.com.jsn.noleggio.modules.locacao.pattern;

import java.text.NumberFormat;

import br.com.jsn.noleggio.main.util.SystemUtil;

public class MultaVO {
	private double preco;
	private String descricao;
	
	public double getPreco() {
		return preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getPrecoDisplay() {
		return NumberFormat.getCurrencyInstance(SystemUtil.LOCALE_BRASIL).format(preco);
	}
}

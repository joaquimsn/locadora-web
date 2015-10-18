package br.com.jsn.noleggio.main.controller;

import java.util.List;

public interface ICrudBean<T> {
	
	public void cadastrar();
	
	public void alterar();
	
	public void excluir();
	
	public boolean validar();
	
	public void habilitarEdicao();
	
	public List<T> getListaTodos();
	
	public void setListaTodos(List<T> lista); 
	
	public T getObjetoSelecionado();
	
	public void setObjetoSelecionado(T objeto);
	
	public List<T> getListaFiltrada();
	
	public void setListaFiltrada(List<T> lista);
}

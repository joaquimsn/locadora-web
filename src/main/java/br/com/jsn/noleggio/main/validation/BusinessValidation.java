package br.com.jsn.noleggio.main.validation;

public abstract class BusinessValidation {
	private boolean validadoComSucesso;
	
	public boolean isValidadoComSucesso() {
		return validadoComSucesso;
	}
	public void setValidadoComSucesso(boolean validadoComSucesso) {
		this.validadoComSucesso = validadoComSucesso;
	}
}

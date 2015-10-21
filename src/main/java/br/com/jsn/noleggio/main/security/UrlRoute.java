package br.com.jsn.noleggio.main.security;

public abstract class UrlRoute {
	
	/**
	 * Página login
	 */
	public static final String LOGIN = "pretty:login";

	/**
	 * Página inical para usuário autenticados
	 */
	public static final String INICIO = "pretty:inicio";

	/**
	 * Página para gerenciar funcionarios
	 */
	public static final String GERENCIAMENTO_FUNCIONARIO = "pretty:gerenciamento-funcionario";
	/**
	 * Página para gerenciar clientes
	 */
	public static final String GERENCIAMENTO_CLIENTE = "pretty:gerenciamento-cliente";
	
	/**
	 * Página para gerenciar clientes
	 */
	public static final String GERENCIAMENTO_AGENCIA = "pretty:gerenciamento-agencia";

	/**
	 * Página para gerenciar veiculos
	 */
	public static final String GERENCIAMENTO_VEICULO = "pretty:gerenciamento-veiculo";
	
	/**
	 * Página para servico de locação
	 */
	public static final String SERVICO_LOCACAO = "pretty:servico-locacao";
}

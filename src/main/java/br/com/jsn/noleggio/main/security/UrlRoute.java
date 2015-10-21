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
	public static final String GERENCIAMENTO_FUNCIONARIO = "prtty:gerenciamento-funcionario";
	/**
	 * Página para gerenciar clientes
	 */
	public static final String GERENCIAMENTO_CLIENTE = "prtty:gerenciamento-cliente";
	
	/**
	 * Página para gerenciar clientes
	 */
	public static final String GERENCIAMENTO_AGENCIA = "prtty:gerenciamento-agencia";

	/**
	 * Página para gerenciar veiculos
	 */
	public static final String GERENCIAMENTO_VEICULO = "prtty:gerenciamento-veiculo";
	
	/**
	 * Página para servico de locação
	 */
	public static final String SERVICO_LOCACAO = "prtty:servico-locacao";
}

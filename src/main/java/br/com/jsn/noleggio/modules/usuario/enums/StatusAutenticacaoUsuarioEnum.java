package br.com.jsn.noleggio.modules.usuario.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusAutenticacaoUsuarioEnum {
	AUTENTICADOR("Acesso autorizado", 1),
	NAO_AUTENTICADO("Acesso negado", 2);
	
	private String display;
	private int value;

	private StatusAutenticacaoUsuarioEnum(String display, int value) {
		this.display = display;
		this.value = value;
	}
	
	public static List<StatusAutenticacaoUsuarioEnum> getEnumList() {
		List<StatusAutenticacaoUsuarioEnum> lista = new ArrayList<StatusAutenticacaoUsuarioEnum>();
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			lista.add(objeto);
		}
		return lista;
	}
	
	public static List<Integer> getValueList() {
		List<Integer> lista = new ArrayList<Integer>();
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			lista.add(objeto.value);
		}
		return lista;
	}
	
	public static List<String> getDisplayList() {
		List<String> lista = new ArrayList<String>();
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			lista.add(objeto.display);
		}
		return lista;
	}
	
	public static int getValueByDisplay(String display) {
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			if(objeto.display.equals(display)) {
				return objeto.value;
			}
		}
		return 0;
	}
	
	public static String getDisplayByValue(Integer value) {
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto.display;
			}
		}
		return null;
	}
	
	public static StatusAutenticacaoUsuarioEnum getEnumByValue(Integer value) {
		for(StatusAutenticacaoUsuarioEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto;
			}
		}
		return null;
	}

	public String getDisplay() {
		return display;
	}

	public int getValue() {
		return value;
	}
}

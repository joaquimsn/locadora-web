package br.com.jsn.noleggio.modules.locacao.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusLocacaoEnum {
	ABERTA("Aberta", 1),
	ENCERRADA("Encerrada", 2),
	ATRASADA("Atrasada", 3);
	
	private String display;
	private int value;
	
	private StatusLocacaoEnum(String display, int value) {
		this.display = display;
		this.value = value;
	}
	
	public static List<Integer> getValueList() {
		List<Integer> lista = new ArrayList<Integer>();
		for(StatusLocacaoEnum objeto : values()) {
			lista.add(objeto.value);
		}
		return lista;
	}
	
	public static List<String> getDisplayList() {
		List<String> lista = new ArrayList<String>();
		for(StatusLocacaoEnum objeto : values()) {
			lista.add(objeto.display);
		}
		return lista;
	}
	
	public static int getValueByDisplay(String display) {
		for(StatusLocacaoEnum objeto : values()) {
			if(objeto.display.equals(display)) {
				return objeto.value;
			}
		}
		return 0;
	}
	
	public static String getDisplayByValue(Integer value) {
		for(StatusLocacaoEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto.display;
			}
		}
		return null;
	}
	
	public static StatusLocacaoEnum getEnumByValue(Integer value) {
		for(StatusLocacaoEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto;
			}
		}
		return null;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}


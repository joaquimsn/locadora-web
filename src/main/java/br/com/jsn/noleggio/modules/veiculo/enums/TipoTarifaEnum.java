package br.com.jsn.noleggio.modules.veiculo.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoTarifaEnum {
	CONTROLADA("Controlada", 1),
	LIVRE("Livre", 2);
	
	private String display;
	private int value;
	
	private TipoTarifaEnum(String display, int value) {
		this.display = display;
		this.value = value;
	}
	
	public static List<TipoTarifaEnum> getEnumList() {
		List<TipoTarifaEnum> lista = new ArrayList<TipoTarifaEnum>();
		for(TipoTarifaEnum objeto : values()) {
			lista.add(objeto);
		}
		return lista;
	}

	public static List<Integer> getValueList() {
		List<Integer> lista = new ArrayList<Integer>();
		for(TipoTarifaEnum objeto : values()) {
			lista.add(objeto.value);
		}
		return lista;
	}
	
	public static List<String> getDisplayList() {
		List<String> lista = new ArrayList<String>();
		for(TipoTarifaEnum objeto : values()) {
			lista.add(objeto.display);
		}
		return lista;
	}
	
	public static int getValueByDisplay(String display) {
		for(TipoTarifaEnum objeto : values()) {
			if(objeto.display.equals(display)) {
				return objeto.value;
			}
		}
		return 0;
	}
	
	public static String getDisplayByValue(Integer value) {
		for(TipoTarifaEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto.display;
			}
		}
		return null;
	}
	
	public static TipoTarifaEnum getEnumByValue(Integer value) {
		for(TipoTarifaEnum objeto : values()) {
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


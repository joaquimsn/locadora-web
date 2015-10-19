package br.com.jsn.noleggio.modules.veiculo.enums;

import java.util.ArrayList;
import java.util.List;

public enum AcessorioVeiculoEnum {
	GPS("GPS", 1),
	CADEIRA_BEBE("", 2),
	MP3_PLAYER("MP3 Plaer", 3),
	DVD("DVD", 4),
	AIR_BAG("Air Bag", 5),
	TRIO_ELETRICO("Trio Elétrico", 6),
	MOTORISTA("Motorista", 7);
	
	private String display;
	private int value;
	
	private AcessorioVeiculoEnum(String display, int value) {
		this.display = display;
		this.value = value;
	}
	
	public static List<Integer> getValueList() {
		List<Integer> lista = new ArrayList<Integer>();
		for(AcessorioVeiculoEnum objeto : values()) {
			lista.add(objeto.value);
		}
		return lista;
	}
	
	public static List<String> getDisplayList() {
		List<String> lista = new ArrayList<String>();
		for(AcessorioVeiculoEnum objeto : values()) {
			lista.add(objeto.display);
		}
		
		return lista;
	}
	
	public static List<AcessorioVeiculoEnum> getEnumList() {
		List<AcessorioVeiculoEnum> lista = new ArrayList<AcessorioVeiculoEnum>();
		for(AcessorioVeiculoEnum objeto : values()) {
			lista.add(objeto);
		}
		return lista;
	}
	
	public static int getValueByDisplay(String display) {
		for(AcessorioVeiculoEnum objeto : values()) {
			if(objeto.display.equals(display)) {
				return objeto.value;
			}
		}
		return 0;
	}
	
	public static String getDisplayByValue(Integer value) {
		for(AcessorioVeiculoEnum objeto : values()) {
			if(objeto.value == value.intValue()) {
				return objeto.display;
			}
		}
		return null;
	}
	
	public static AcessorioVeiculoEnum getEnumByValue(Integer value) {
		for(AcessorioVeiculoEnum objeto : values()) {
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


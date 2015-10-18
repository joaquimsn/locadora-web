package br.com.jsn.noleggio.main.util;

public class NumberUtil {
	public static int converterToPositive(int number) {
		return number < 0 ? (number * -1) : number;
	}
}

package br.com.jsn.noleggio.main.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
	
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * @author Joaquim Neto
	 * @param year amount of year
	 * @return past date form current date - year
	 */
	public static LocalDate pastDateFromCurrent(int year) {
		LocalDate localDate = LocalDate.now();
		localDate = LocalDate.of(localDate.getYear() - NumberUtil.converterToPositive(year), localDate.getMonthValue(), localDate.getDayOfMonth());
		return localDate;
	}
}
	

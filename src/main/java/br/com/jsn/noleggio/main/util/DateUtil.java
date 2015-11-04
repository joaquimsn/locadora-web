package br.com.jsn.noleggio.main.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public static int intervalInDays(Date less, Date greater) {
		if (less == null || greater == null) {
			return 0;
		}
		
		LocalDateTime lessLocalDate = LocalDateTime.ofInstant(less.toInstant(), ZoneId.systemDefault());
		LocalDateTime greaterLocalDate = LocalDateTime.ofInstant(greater.toInstant(), ZoneId.systemDefault());
		
		Duration duration = Duration.between(lessLocalDate, greaterLocalDate);
		
		return Long.valueOf(duration.toDays()).intValue();
	}
}
	

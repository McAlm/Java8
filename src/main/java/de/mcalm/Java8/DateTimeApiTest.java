package de.mcalm.Java8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class DateTimeApiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
		System.out.println(now.getClass().getName());
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//		availableZoneIds.stream().sorted().forEach(System.out::println);
		
		ZonedDateTime zdt = ZonedDateTime.now();
		long bisSilvester = zdt.until(zdt.withMonth(12).withDayOfMonth(31), ChronoUnit.DAYS);
		
		System.out.println(bisSilvester + "Tage bis Silvester");

		System.out.println(now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
		
		
	}
}

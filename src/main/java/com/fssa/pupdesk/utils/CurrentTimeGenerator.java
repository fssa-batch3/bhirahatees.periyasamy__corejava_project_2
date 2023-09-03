package com.fssa.pupdesk.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeGenerator {

	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}

package com.fssa.pupdesk.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for generating the current date and time in a specific format.
 */
public class CurrentTimeGenerator {

	/**
	 * Retrieves the current date and time formatted as "yyyy-MM-dd HH:mm:ss".
	 *
	 * @return A string representing the current date and time.
	 */
	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}

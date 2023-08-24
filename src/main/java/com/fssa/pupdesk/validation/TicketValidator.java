package com.fssa.pupdesk.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

public class TicketValidator {
	public static boolean validateTicket(Ticket ticket) throws InvalidTicketException {

		if (validateTicketEmail(ticket.getFrom()) && validateTicketEmail(ticket.getTo())
				&& validateTime(ticket.getCreatedTime()) && validateSummary(ticket.getSummary())
				&& validateTicketId(ticket.getTicketId()) && validatePriority(ticket.getPriority())
				&& validateStatus(ticket.getStatus())) {
			return true;
		} else {
			throw new InvalidTicketException("Failed to create Ticket");
		}

	}

	public static boolean validateTime(String dateTimeString) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
		return !parsedDateTime.isAfter(currentDateTime);
	}

	public static boolean validateTicketEmail(String email) {
		return UserValidator.validateEmail(email);

	}

	public static boolean validateSummary(String summary) {
		if (summary == null) {
			return false;
		}
		String regex = "^.{2,60}$";
		return Pattern.matches(regex, summary);
	}

	public static boolean validatePriority(String priority) {
		return (priority.equalsIgnoreCase("high") || priority.equalsIgnoreCase("medium")
				|| priority.equalsIgnoreCase("low"));

	}

	public static boolean validateStatus(String status) {
		return (status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("open")
				|| status.equalsIgnoreCase("on progress") || status.equalsIgnoreCase("closed")); 
	}

	public static boolean validateTicketId(String ticketId) {
		String regex = "[0-9A-F]{2}([0-9A-F]{2}){6}";
		return Pattern.matches(regex, ticketId);

	}
}

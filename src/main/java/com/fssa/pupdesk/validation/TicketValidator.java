package com.fssa.pupdesk.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

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

	public static boolean validateTime(String dateTimeString) throws InvalidTicketException {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
		if (!parsedDateTime.isAfter(currentDateTime)) {
			return true;
		}else {
			throw new InvalidTicketException("Something happens in Date");
		}
	}

	public static boolean validateTicketEmail(String email) throws InvalidTicketException {
		try {
			return UserValidator.validateEmail(email);
				
		} catch (InvalidUserException e) {
			throw new InvalidTicketException("Email is Invalid");
		}

	}

	public static boolean validateSummary(String summary) throws InvalidTicketException {
		if (summary == null) {
			throw new InvalidTicketException("Summary Must contain in the ticket");
		}
		String regex = "^.{2,60}$";
		if(Pattern.matches(regex, summary))
			return true;
		else
			throw new InvalidTicketException("Summary is not more than 60 characters");
	}

	public static boolean validatePriority(String priority) throws InvalidTicketException {
		if (priority.equalsIgnoreCase("high") || priority.equalsIgnoreCase("medium")
				|| priority.equalsIgnoreCase("low")) {
			return true;
		}else {
			throw new InvalidTicketException("Something happends in the priority");
		}

	}

	public static boolean validateStatus(String status) throws InvalidTicketException {
		if (status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("open")
				|| status.equalsIgnoreCase("on progress") || status.equalsIgnoreCase("closed")) {
			return true;
		}else {
			throw new InvalidTicketException("Something happends in status");
		}
	}

	public static boolean validateTicketId(String ticketId) throws InvalidTicketException {
		String regex = "[0-9A-F]{2}([0-9A-F]{2}){6}";
		if(Pattern.matches(regex, ticketId)) {
			return true;
		}else {
			throw new InvalidTicketException("Failed to generate Ticket ID");
		}

	}

}

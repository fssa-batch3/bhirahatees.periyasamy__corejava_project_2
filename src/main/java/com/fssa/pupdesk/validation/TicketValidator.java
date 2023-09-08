package com.fssa.pupdesk.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

/**
 * Utility class for validating ticket-related data.
 */
public class TicketValidator {

	/**
	 * Validates a Ticket object for correctness.
	 *
	 * @param ticket The Ticket object to validate.
	 * @return true if the ticket is valid, false otherwise.
	 * @throws InvalidTicketException If the ticket is invalid.
	 */
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

	/**
	 * Validates a date and time string format (yyyy-MM-dd HH:mm:ss).
	 *
	 * @param dateTimeString The date and time string to validate.
	 * @return true if the format is valid, false otherwise.
	 * @throws InvalidTicketException If the format is invalid.
	 */
	public static boolean validateTime(String dateTimeString) throws InvalidTicketException {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
		if (!parsedDateTime.isAfter(currentDateTime)) {
			return true;
		} else {
			throw new InvalidTicketException("Date and time format is invalid");
		}
	}

	/**
	 * Validates an email address for correctness using regular expression.
	 *
	 * @param email The email address to validate.
	 * @return true if the email is valid, false otherwise.
	 * @throws InvalidTicketException If the email is invalid.
	 */
	public static boolean validateTicketEmail(String email) throws InvalidTicketException {
		try {
			return UserValidator.validateEmail(email);
		} catch (InvalidUserException e) {
			throw new InvalidTicketException("Email is Invalid");
		}
	}

	/**
	 * Validates the summary field of a ticket (2 to 60 characters).
	 *
	 * @param summary The summary to validate.
	 * @return true if the summary is valid, false otherwise.
	 * @throws InvalidTicketException If the summary is invalid.
	 */
	public static boolean validateSummary(String summary) throws InvalidTicketException {
		if (summary == null) {
			throw new InvalidTicketException("Summary must be provided in the ticket");
		}
		String regex = "^.{2,60}$"; // Summary should be 2 to 60 characters long
		if (Pattern.matches(regex, summary))
			return true;
		else
			throw new InvalidTicketException("Summary is not between 2 and 60 characters");
	}

	/**
	 * Validates the priority field of a ticket (high, medium, or low).
	 *
	 * @param priority The priority to validate.
	 * @return true if the priority is valid, false otherwise.
	 * @throws InvalidTicketException If the priority is invalid.
	 */
	public static boolean validatePriority(String priority) throws InvalidTicketException {
		if (priority.equalsIgnoreCase("high") || priority.equalsIgnoreCase("medium")
				|| priority.equalsIgnoreCase("low")) {
			return true;
		} else {
			throw new InvalidTicketException("Priority is invalid");
		}
	}

	/**
	 * Validates the status field of a ticket (pending, open, on progress, or
	 * closed).
	 *
	 * @param status The status to validate.
	 * @return true if the status is valid, false otherwise.
	 * @throws InvalidTicketException If the status is invalid.
	 */
	public static boolean validateStatus(String status) throws InvalidTicketException {
		if (status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("open")
				|| status.equalsIgnoreCase("on progress") || status.equalsIgnoreCase("closed")) {
			return true;
		} else {
			throw new InvalidTicketException("Status is invalid");
		}
	}

	/**
	 * Validates the ticket ID format using regular expression.
	 *
	 * @param ticketId The ticket ID to validate.
	 * @return true if the format is valid, false otherwise.
	 * @throws InvalidTicketException If the format is invalid.
	 */
	public static boolean validateTicketId(String ticketId) throws InvalidTicketException {
		String regex = "[0-9A-F]{2}([0-9A-F]{2}){6}"; // Ticket ID should match the specified format
		if (Pattern.matches(regex, ticketId)) {
			return true;
		} else {
			throw new InvalidTicketException("Ticket ID format is invalid");
		}
	}
}

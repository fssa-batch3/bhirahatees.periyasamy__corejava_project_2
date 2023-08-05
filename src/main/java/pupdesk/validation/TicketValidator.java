package pupdesk.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pupdesk.model.Ticket;
import pupdesk.validation.exceptions.InvalidTicketException;

public class TicketValidator {
	public static boolean validateTicket(Ticket ticket) throws InvalidTicketException {

		if (validateEmail(ticket.getFrom()) && validateEmail(ticket.getTo()) && validateTime(ticket.getCreateTime())
				&& validateSummary(ticket.getSummary()) && validateTicketId(ticket.getTicketId())
				&& validatePriority(ticket.getPriority()) && validateStatus(ticket.getStatus())) {
			return true;
		} else {
			throw new InvalidTicketException("Falide to create Ticket");
		}

	}

	public static boolean validateTime(String dateTimeString) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);

		return !parsedDateTime.isAfter(currentDateTime);
	}

	public static boolean validateEmail(String email) {
		boolean isMatch = false;

		if (email == null)
			return false;
		String regex = "^.*@.*\\..*$";
		isMatch = Pattern.matches(regex, email);
		if (isMatch) {
			System.out.println("The email address is: Valid");
		} else {
			System.out.println("The email address is: Invalid");
		}
		return isMatch;

	}

	public static boolean validateSummary(String summary) {
		boolean isMatch = false;
		String regex = "^.{2,60}$";
		isMatch = Pattern.matches(regex, summary);
		if (isMatch) {
			System.out.println("Summary is Valid");
		} else {
			System.out.println("Summary is Invalid");
		}
		return isMatch;
	}

	public static boolean validatePriority(String priority) {
		if (priority.toLowerCase().equals("high") || priority.toLowerCase().equals("medium")
				|| priority.toLowerCase().equals("low")) {
			System.out.println("Valid Priority");
			return true;
		}
		System.out.println("Invalid Priority");
		return false;
	}

	public static boolean validateStatus(String status) {
		if (status.toLowerCase().equals("pending") || status.toLowerCase().equals("on progress")
				|| status.toLowerCase().equals("success")) {
			System.out.println("Valid Status");
			return true;
		}
		System.out.println("Invalid Status");
		return false;
	}

	public static boolean validateTicketId(String ticketId) {
		boolean isMatch = false;
		String regex = "^[A-Z0-9].{1,13}$";
		isMatch = Pattern.matches(regex, ticketId);
		if (isMatch) {
			System.out.println("Ticket ID is Valid");
		} else {
			System.out.println("Ticket ID is Invalid");
		}
		return isMatch;

	}

}

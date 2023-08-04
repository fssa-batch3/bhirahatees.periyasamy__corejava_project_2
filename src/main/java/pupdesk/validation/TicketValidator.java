package pupdesk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketValidator {

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

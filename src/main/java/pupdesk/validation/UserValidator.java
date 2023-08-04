package pupdesk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import pupdesk.DAO.UserDAO;
import pupdesk.model.User;
import pupdesk.validation.exceptions.InvalidUserException;

public class UserValidator {

	public static boolean validateUser(User user1) throws InvalidUserException {

		if (user1 != null && validateFirstname(user1.getFirstname())&& validateFirstname(user1.getLastname()) && validateTeamCode(user1.getTeamCode())&& validatePassword(user1.getPassword())
				&& validateEmail(user1.getEmail())) {
			return true;
		} else {
			throw new InvalidUserException("User details not valid");
		}

	}

	public static boolean validateFirstname (String name) {
		boolean match = false;

		if (name == null)
			return false;

		String regex = "^[A-Z]{1,}[a-z]{0,16}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name);
		match = m.matches();
		if (match) {
			System.out.println("name is valid.");
		} else {
			System.out.println("user name is not valid");
		}

		return match;
	}

	public static boolean validatePassword(String password) {
		boolean match = false;

		if (password == null)
			return false;

		String pattern_string = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}$";
		Pattern pattern = Pattern.compile(pattern_string);
		Matcher check = pattern.matcher(password);
		
		match = check.matches();
		

		if (match) {

			System.out.println("Valid password.");
		} else {
			System.out.println("Invalid password.");
		}

		return match;
	}

	
	public static boolean validateTeamCode(String password) {
		boolean match = false;

		if (password == null)
			return false;

		String pattern_string = "^[A-Z0-9]{6,6}$";
		match = Pattern.matches(pattern_string, password);

		if (match) {

			System.out.println("Valid Team Code");
		} else {
			System.out.println("Invalid Team Code.");
		}

		return match;
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

	public static void main(String[] args) {
		UserValidator.validateTeamCode("*&&%%");
	}
}

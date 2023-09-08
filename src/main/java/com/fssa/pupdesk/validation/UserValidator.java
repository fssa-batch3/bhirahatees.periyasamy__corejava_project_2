package com.fssa.pupdesk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

/**
 * Utility class for validating user-related data.
 */
public class UserValidator {

	/**
	 * Validates a User object for correctness.
	 *
	 * @param user The User object to validate.
	 * @return true if the user is valid, false otherwise.
	 * @throws InvalidUserException If the user is invalid.
	 */
	public static boolean validateUser(User user) throws InvalidUserException {
		try {
			if (user != null && validateFirstname(user.getFirstname()) && validateFirstname(user.getLastname())
					&& validateTeamCode(user.getTeamCode()) && validatePassword(user.getPassword())
					&& validateEmail(user.getEmail())) {
				return true;
			}
		} catch (InvalidUserException e) {
			throw new InvalidUserException(e.getMessage());
		}
		return false;
	}

	/**
	 * Validates a user's first name or last name.
	 *
	 * @param name The name to validate.
	 * @return true if the name is valid, false otherwise.
	 * @throws InvalidUserException If the name is invalid.
	 */
	public static boolean validateFirstname(String name) throws InvalidUserException {
		if (name == null)
			return false;

		String regex = "^[A-Z]+[a-z]{0,16}$"; // First name should start with an uppercase letter and have up to 17
												// characters
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name);
		if (m.matches())
			return true;
		else
			throw new InvalidUserException("Invalid Firstname or Lastname");
	}

	/**
	 * Validates a user's password.
	 *
	 * @param password The password to validate.
	 * @return true if the password is valid, false otherwise.
	 * @throws InvalidUserException If the password is invalid.
	 */
	public static boolean validatePassword(String password) throws InvalidUserException {
		if (password == null)
			return false;

		String patternString = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}$";
		Pattern pattern = Pattern.compile(patternString);
		Matcher check = pattern.matcher(password);

		if (check.matches())
			return true;
		else
			throw new InvalidUserException(
					"Password should contain at least one lowercase, one uppercase, one digit, one special character, and be at least 8 characters long.");
	}

	/**
	 * Validates a user's team code.
	 *
	 * @param teamcode The team code to validate.
	 * @return true if the team code is valid, false otherwise.
	 * @throws InvalidUserException If the team code is invalid.
	 */
	public static boolean validateTeamCode(String teamcode) throws InvalidUserException {
		if (teamcode == null)
			return false;

		String patternString = "^[A-Z0-9]{6}$"; // Team code should consist of 6 uppercase letters or digits
		if (Pattern.matches(patternString, teamcode))
			return true;
		else
			throw new InvalidUserException("Team code should not contain special characters or not only alphabets");
	}

	/**
	 * Validates a user's email address.
	 *
	 * @param email The email address to validate.
	 * @return true if the email address is valid, false otherwise.
	 * @throws InvalidUserException If the email address is invalid.
	 */
	public static boolean validateEmail(String email) throws InvalidUserException {
		if (email == null)
			return false;

		String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email format validation
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches())
				throw new InvalidUserException("Invalid email");
			else
				return true;
		} catch (PatternSyntaxException e) {
			return false;
		}
	}
}

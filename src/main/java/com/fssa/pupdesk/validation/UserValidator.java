package com.fssa.pupdesk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

public class UserValidator {

	public static boolean validateUser(User user1) throws InvalidUserException {

		if (user1 != null && validateFirstname(user1.getFirstname()) && validateFirstname(user1.getLastname())
				&& validateTeamCode(user1.getTeamCode()) && validatePassword(user1.getPassword())
				&& validateEmail(user1.getEmail())) {
			return true;
		} else {
			throw new InvalidUserException("User details not valid");
		}

	}

	public static boolean validateFirstname(String name) {

		if (name == null)
			return false;

		String regex = "^[A-Z]+[a-z]{0,16}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name);
		return m.matches();
	}

	public static boolean validatePassword(String password) {

		if (password == null)
			return false;

		String patternString = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}$";
		Pattern pattern = Pattern.compile(patternString);
		Matcher check = pattern.matcher(password);

		return check.matches();
	}

	public static boolean validateTeamCode(String password) {
		if (password == null)
			return false;
		String patternString = "^[A-Z0-9]{6}$";
		return Pattern.matches(patternString, password);
	}

	public static boolean validateEmail(String email) {

		if (email == null)
			return false;
		String regex = "^.*@.*\\..*$";
		return Pattern.matches(regex, email);

	}

}

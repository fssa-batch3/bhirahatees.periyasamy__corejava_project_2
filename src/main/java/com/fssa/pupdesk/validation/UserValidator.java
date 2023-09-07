package com.fssa.pupdesk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

public class UserValidator {

	public static boolean validateUser(User user1) throws InvalidUserException {

		try {
			if (user1 != null && validateFirstname(user1.getFirstname()) && validateFirstname(user1.getLastname())
					&& validateTeamCode(user1.getTeamCode()) && validatePassword(user1.getPassword())
					&& validateEmail(user1.getEmail())) {
				return true;
			}
		} catch (InvalidUserException e) {
			throw new InvalidUserException(e.getMessage());
		}
		return false;

	}

	public static boolean validateFirstname(String name) throws InvalidUserException {

		if (name == null)
			return false;

		String regex = "^[A-Z]+[a-z]{0,16}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name);
		if (m.matches())
			return true;
		else
			throw new InvalidUserException("Invalid Firstname or Lastname");
	}

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
					"Invalid Password Password contain at least one lowercase letter, one uppercase letter, one digit, one special character from the set [@#$%^&+=]");
	}

	public static boolean validateTeamCode(String teamcode) throws InvalidUserException {
		if (teamcode == null)
			return false;
		String patternString = "^[A-Z0-9]{6}$";
		if (Pattern.matches(patternString, teamcode))
			return true;
		else
			throw new InvalidUserException("Teamcode not contains special characters");
	}

	public static boolean validateEmail(String email) throws InvalidUserException {

		if (email == null)
			return false;
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

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

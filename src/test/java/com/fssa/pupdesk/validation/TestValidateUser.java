package com.fssa.pupdesk.validation;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.validation.exceptions.InvalidUserException;
import com.fssa.pupdesk.model.User;

class TestValidateUser {

	// Test case for validating a valid email
	@Test
	void testValidateFirstEmailTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateEmail("bhirahatees.periyayasamy@fssa.freshworks.com"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating an invalid email
	@Test
	void testValidateFirstEmailTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class,
				() -> validation.validateEmail("bhirahatees.periysamy.fssa.freshworks.com"));
	}

	// Test case for validating a valid first name
	@Test
	void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateFirstname("Bhirahatees"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating an invalid first name
	@Test
	void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateFirstname("bhiraHatees"));
	}

	// Test case for validating a valid last name
	@Test
	void testValidateLastNameTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateFirstname("Periysamy"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating an invalid last name
	@Test
	void testValidateLastNameTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateFirstname("PeriYasamy"));
	}

	// Test case for validating a valid password
	@Test
	void testValidPassword() {
		try {
			assertTrue(UserValidator.validatePassword("Bhirahatees@123"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating a password without special characters
	@Test
	void testInvalidPasswordWithoutSpecialCharacters() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("Bhirahateesz123"));
	}

	// Test case for validating a password without numbers
	@Test
	void testInvalidPasswordWithoutNumbers() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("bhirahatees@"));
	}

	// Test case for validating a password without capital letters
	@Test
	void testInvalidPasswordWithoutCapitalLetters() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("bhirahatees123"));
	}

	// Test case for validating a password without small letters
	@Test
	void testInvalidPasswordWithoutSmallLetters() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("BHIRAHATEES123"));
	}

	// Test case for validating a password with shorter length
	@Test
	void testInvalidPasswordShorterLength() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("Bhi123"));
	}

	// Test case for validating a valid team code
	@Test
	void testValidateTeamCodeTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateTeamCode("H2GH21"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating an invalid team code
	@Test
	void testValidateTeamCodeTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateTeamCode("H#Q)VA"));
	}

	// Test case for validating a valid user
	@Test
	void testValidateUserPass() {
		User user = new User("Bhirahateesvaran", "Periyasamy", "bhirahatees.periyasamy@fssa.freshworks.com", "H2GH21",
				"Bhirahatees@123");
		try {
			assertTrue(UserValidator.validateUser(user));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	// Test case for validating an invalid user
	@Test
	void testValidateUserFail() {
		User user = new User("bhirahateesvaran", "Periyasamy", "bhirahatees.periyasamy@fssa.freshworks.com", "H2GH21",
				"Bhirahatees@123");
		assertThrows(InvalidUserException.class, () -> UserValidator.validateUser(user));
	}
}

package com.fssa.pupdesk.validation;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.validation.exceptions.InvalidUserException;
import com.fssa.pupdesk.model.User;

class TestValidateUser {
	@Test
	void testValidateFirstEmailTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateEmail("bhirahatees.periyayasamy@fssa.freshworks.com"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testValidateFirstEmailTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class,
				() -> validation.validateEmail("bhirahatees.periysamy.fssa.freshworks.com"));
	}

	@Test
	void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateFirstname("Bhirahatees"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateFirstname("bhiraHatees"));
	}

	@Test
	void testValidateLastNameTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateFirstname("Periysamy"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testValidateLastNameTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateFirstname("PeriYasamy"));
	}

	@Test
	void testValidPassword() {

		try {
			assertTrue(UserValidator.validatePassword("Bhirahatees@123"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testInvalidPasswordWithoutSpecialCharacters() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("Bhirahateesz123"));

	}

	@Test
	void testInvalidPasswordWithoutNumbers() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("bhirahatees@"));

	}

	@Test
	void testInvalidPasswordWithoutCapitalLetters() {
		assertThrows(InvalidUserException.class, () -> UserValidator.validatePassword("bhirahatees123"));

	}

	@Test
	void testInvalidPasswordWithoutSmallLetters() {
	assertThrows(InvalidUserException.class,()->UserValidator.validatePassword("BHIRAHATEES123"));

	}

	@Test
	void testInvalidPasswordShorterLength() {
	assertThrows(InvalidUserException.class,()->UserValidator.validatePassword("Bhi123"));

	}

	@Test
	void testValidateTeamCodeTestPass() {
		UserValidator validation = new UserValidator();
		try {
			assertTrue(validation.validateTeamCode("H2GH21"));
		} catch (InvalidUserException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testValidateTeamCodeTestFail() {
		UserValidator validation = new UserValidator();
		assertThrows(InvalidUserException.class, () -> validation.validateTeamCode("H#Q)VA"));
	}
	
	@Test
	void testValidateUserPass() {
		User user = new User("Bhirahateesvaran", "Periyasamy","bhirahatees.periyasamy@fssa.freshworks.com", "H2GH21", "Bhirahatees@123");
		try {
			assertTrue(UserValidator.validateUser(user));
		} catch (InvalidUserException e) {
		fail(e.getMessage());
		}
	}
	@Test
	void testValidateUserFail() {
		User user = new User("bhirahateesvaran", "Periyasamy","bhirahatees.periyasamy@fssa.freshworks.com", "H2GH21", "Bhirahatees@123");
	    assertThrows(InvalidUserException.class,()-> UserValidator.validateUser(user));
	}

}

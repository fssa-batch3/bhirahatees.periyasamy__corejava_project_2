package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TestValidateUser {
	@Test
	 void testValidateFirstEmailTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateEmail("bhirahatees.periyayasamy@fssa.freshworks.com"));

	}

	@Test
	 void testValidateFirstEmailTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateEmail("bhirahatees.periysamy.fssa.freshworks.com"));
	}

	@Test
	 void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateFirstname("Bhirahatees"));
	}

	@Test
	 void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateFirstname("bhiraHatees"));
	}

	@Test
	 void testValidateLastNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateFirstname("Periysamy"));
	}

	@Test
	 void testValidateLastNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateFirstname("PeriYasamy"));
	}

	@Test
	 void testValidPassword() {

		assertTrue(UserValidator.validatePassword("Password@123"));

	}

	@Test
	 void testInvalidPasswordWithoutSpecialCharacters() {
		assertFalse(UserValidator.validatePassword("Password123"));

	}

	@Test
	 void testInvalidPasswordWithoutNumbers() {
		assertFalse(UserValidator.validatePassword("Password@"));

	}

	@Test
	 void testInvalidPasswordWithoutCapitalLetters() {
		assertFalse(UserValidator.validatePassword("password123"));

	}

	@Test
	 void testInvalidPasswordWithoutSmallLetters() {
		assertFalse(UserValidator.validatePassword("PASSWORD@123"));

	}

	@Test
	 void testInvalidPasswordShorterLength() {
		assertFalse(UserValidator.validatePassword("Pas@123"));

	}

	@Test
	 void testValidateTeamCodeTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateTeamCode("H2GH21"));
	}

	@Test
	 void testValidateTeamCodeTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateTeamCode("H#Q)VA"));
	}

}

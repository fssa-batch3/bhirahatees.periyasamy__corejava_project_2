package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestValidateUser {
	@Test
	public void testValidateFirstEmailTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateEmail("bhirahatees.periyayasamy@fssa.freshworks.com"));

	}

	@Test
	public void testValidateFirstEmailTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateEmail("bhirahatees.periysamy.fssa.freshworks.com"));
	}

	@Test
	public void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateFirstname("Bhirahatees"));
	}

	@Test
	public void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateFirstname("bhiraHatees"));
	}

	@Test
	public void testValidateLastNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateFirstname("Periysamy"));
	}

	@Test
	public void testValidateLastNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateFirstname("PeriYasamy"));
	}

	@Test
	public void testValidPassword() {

		assertTrue(UserValidator.validatePassword("Password@123"));

	}

	@Test
	public void testInvalidPasswordWithoutSpecialCharacters() {
		assertFalse(UserValidator.validatePassword("Password123"));

	}

	@Test
	public void testInvalidPasswordWithoutNumbers() {
		assertFalse(UserValidator.validatePassword("Password@"));

	}

	@Test
	public void testInvalidPasswordWithoutCapitalLetters() {
		assertFalse(UserValidator.validatePassword("password123"));

	}

	@Test
	public void testInvalidPasswordWithoutSmallLetters() {
		assertFalse(UserValidator.validatePassword("PASSWORD@123"));

	}

	@Test
	public void testInvalidPasswordShorterLength() {
		assertFalse(UserValidator.validatePassword("Pas@123"));

	}

	@Test
	public void testValidateTeamCodeTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateTeamCode("H2GH21"));
	}

	@Test
	public void testValidateTeamCodeTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateTeamCode("H#Q)VA"));
	}

}

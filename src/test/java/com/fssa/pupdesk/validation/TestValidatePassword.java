package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidatePassword {

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

}

package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateTeamCode {
	@Test
	public void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateTeamCode("H2GH21"));
	}

	@Test
	public void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateTeamCode("H#Q)VA"));
	}
}

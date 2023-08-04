package pupdesk.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import pupdesk.validation.exceptions.InvalidUserException;

public class TestValidateName {
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
}

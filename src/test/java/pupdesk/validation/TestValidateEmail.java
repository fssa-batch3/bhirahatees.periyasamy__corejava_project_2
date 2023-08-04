package pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateEmail {
	@Test
	public void testValidateFirstNameTestPass() {
		UserValidator validation = new UserValidator();
		assertTrue(validation.validateEmail("bhirahatees.periyayasamy@fssa.freshworks.com"));
	}

	@Test
	public void testValidateFirstNameTestFail() {
		UserValidator validation = new UserValidator();
		assertFalse(validation.validateEmail("bhirahatees.periysamy.fssa.freshworks.com"));
	}
}

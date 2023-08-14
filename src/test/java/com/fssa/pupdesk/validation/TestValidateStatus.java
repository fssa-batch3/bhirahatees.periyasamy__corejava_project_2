package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateStatus {
	@Test
	public void TestValidateStatusPass() {
		TicketValidator validate = new TicketValidator();
		assertTrue(validate.validateStatus("On Progress"));
	}

	@Test
	public void TestValidateStatusFail() {
		TicketValidator validate = new TicketValidator();
		assertFalse(validate.validateStatus("Low"));
	}
}

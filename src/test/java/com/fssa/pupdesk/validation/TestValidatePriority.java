package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidatePriority {
	@Test
	public void TestValidatePriorityPass() {
		TicketValidator validate = new TicketValidator();
		assertTrue(validate.validatePriority("High"));
	}

	@Test
	public void TestValidatePriorityFail() {
		TicketValidator validate = new TicketValidator();
		assertFalse(validate.validatePriority("On Progress"));
	}
}

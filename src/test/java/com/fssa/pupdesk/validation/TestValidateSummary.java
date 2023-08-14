package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateSummary {
	@Test
	public void TestValidateSummaryPass() {
		TicketValidator validate = new TicketValidator();
		assertTrue(validate.validateSummary("Request for leave for Half Day"));
	}

	@Test
	public void TestValidateSummaryFail() {
		TicketValidator validate = new TicketValidator();
		assertFalse(validate.validateSummary(null));
	}

}

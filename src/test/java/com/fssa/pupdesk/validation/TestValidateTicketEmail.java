package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateTicketEmail {
	@Test
	public void TestTicketValidEmailPass() {
		assertTrue(new TicketValidator().validateTicketEmail("bhirahatees.periysamay@fssa.freshworks.com"));
	}
	@Test
	public void TestTicketValidEmailFail() {
		assertFalse(new TicketValidator().validateTicketEmail("bhirahatees.periysamayfssa.freshworks.com"));
	}
}


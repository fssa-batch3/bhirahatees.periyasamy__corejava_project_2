package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestvalidateTicketID {
	@Test
	public void TestvalidateTicketIDPass() {
		assertTrue(new TicketValidator().validateTicketId("A6F83J2G387G23"));
	}

	@Test
	public void TestvalidateTicketIDFail() {
		assertFalse(new TicketValidator().validateTicketId("A6H9F83J2G387G23"));
	}

}

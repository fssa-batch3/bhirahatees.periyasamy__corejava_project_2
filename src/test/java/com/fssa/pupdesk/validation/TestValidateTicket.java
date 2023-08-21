package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fssa.pupdesk.model.Ticket;
import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

public class TestValidateTicket {
	@Test
	public void TestValidateTicketTimePass() {
		assertTrue(new TicketValidator().validateTime("2023-04-25 06:55:00"));
	}

	public void TestValidateTicketTimeFail() {
		assertFalse(new TicketValidator().validateTime("2024-04-25 06:55:00"));
	}

	@Test
	public void TestvalidateTicketIDPass() {
		assertTrue(new TicketValidator().validateTicketId("A6F83J2G387G23"));
	}

	@Test
	public void TestvalidateTicketIDFail() {
		assertFalse(new TicketValidator().validateTicketId("A6H9F83J2G387G23"));
	}

	@Test
	public void TestTicketValidEmailPass() {
		assertTrue(new TicketValidator().validateTicketEmail("bhirahatees.periysamay@fssa.freshworks.com"));
	}

	@Test
	public void TestTicketValidEmailFail() {
		assertFalse(new TicketValidator().validateTicketEmail("bhirahatees.periysamayfssa.freshworks.com"));
	}

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

	@Test
	public void TestValidateTicketPass() {
		Ticket ticket = new Ticket("bhirahatees.periysamay@fssa.freshworks.com",
				"gowtham.sathayamoorthy@fssa.freshworks.com", "SQL Workbench not working", "High", "Pending",
				"While writing the testcases it's not working");
		try {
			assertTrue(new TicketValidator().validateTicket(ticket));
		} catch (InvalidTicketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestValidateTicketFail() {
		Ticket ticket = new Ticket("bhirahatees.periysamay@fssa.freshworks.com",
				"gowtham.sathayamoorthy@fssa.freshworks.com", "SQL Workbench not working", "Pending", "On Progress",
				"While writing the testcases it's not working");

		assertThrows(InvalidTicketException.class, () -> {
			new TicketValidator().validateTicket(ticket);
		});
	}

}

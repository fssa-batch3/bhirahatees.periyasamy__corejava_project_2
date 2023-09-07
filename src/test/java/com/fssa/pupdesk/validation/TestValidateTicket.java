package com.fssa.pupdesk.validation;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fssa.pupdesk.model.Ticket;
import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

 class TestValidateTicket {
	@Test
	 void TestValidateTicketTimePass() {
		try {
			assertTrue(new TicketValidator().validateTime("2023-04-25 06:55:00"));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestValidateTicketTimeFail() {
		assertThrows(InvalidTicketException.class,()->new TicketValidator().validateTime("2024-04-25 06:55:00"));
	}

	@Test
	 void TestvalidateTicketIDPass() {
		try {
			assertTrue(new TicketValidator().validateTicketId("12E34B9E49E852"));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestvalidateTicketIDFail() {
		assertThrows(InvalidTicketException.class,()->new TicketValidator().validateTicketId("A6H9F83J2G387G23"));
	}

	@Test
	 void TestTicketValidEmailPass() {
		try {
			assertTrue(new TicketValidator().validateTicketEmail("bhirahatees.periysamay@fssa.freshworks.com"));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestTicketValidEmailFail() {
		assertThrows(InvalidTicketException.class,()->new TicketValidator().validateTicketEmail("bhirahatees.periysamayfssa.freshworks.com"));
	}

	@Test
	 void TestValidateSummaryPass() {
		TicketValidator validate = new TicketValidator();
		try {
			assertTrue(validate.validateSummary("Request for leave for Half Day"));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestValidateSummaryFail() {
		TicketValidator validate = new TicketValidator();
		assertThrows(InvalidTicketException.class,()->validate.validateSummary(null));
	}

	@Test
	 void TestValidateStatusPass() {
		TicketValidator validate = new TicketValidator();
		try {
			assertTrue(validate.validateStatus("On Progress"));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestValidateStatusFail() {
		TicketValidator validate = new TicketValidator();
		assertThrows(InvalidTicketException.class,()->validate.validateStatus("Low"));
	}

	@Test
	 void TestValidatePriorityPass() {
		TicketValidator validate = new TicketValidator();
		try {
			assertTrue(validate.validatePriority("High"));
		} catch (InvalidTicketException e) {
		 fail(e.getMessage());
		}
	}

	@Test
	 void TestValidatePriorityFail() {
		TicketValidator validate = new TicketValidator();
		assertThrows(InvalidTicketException.class,()->validate.validatePriority("On Progress"));
	}

	@Test
	 void TestValidateTicketPass() {
		Ticket ticket = new Ticket("bhirahatees.periysamay@fssa.freshworks.com",
				"gowtham.sathayamoorthy@fssa.freshworks.com", "SQL Workbench not working", "High", "Pending",
				"While writing the testcases it's not working");
		try {
			assertTrue(new TicketValidator().validateTicket(ticket));
		} catch (InvalidTicketException e) {
			fail(e.getMessage());
		}
	}

	@Test
	 void TestValidateTicketFail() {
		Ticket ticket = new Ticket("bhirahatees.periysamay@fssa.freshworks.com",
				"gowtham.sathayamoorthy@fssa.freshworks.com", "SQL Workbench not working", "Pending", "On Progress",
				"While writing the testcases it's not working");

		assertThrows(InvalidTicketException.class, () -> {
			new TicketValidator().validateTicket(ticket);
		});
	}

}

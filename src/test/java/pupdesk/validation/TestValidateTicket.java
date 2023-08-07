package pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pupdesk.model.Ticket;
import pupdesk.validation.exceptions.InvalidTicketException;

public class TestValidateTicket {
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

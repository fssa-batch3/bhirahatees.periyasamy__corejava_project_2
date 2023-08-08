package pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pupdesk.model.Ticket;
import pupdesk.services.exceptions.ServiceException;

public class TestCreateTicketService {
	@Test
	public void TestCreateTicketServicePass() {
		TicketService ticketService = new TicketService();
		try {
			assertTrue(ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com",
					"gowtham.sathyamoorthy@fssa.freshworks.com", "I have a find bugs in your code", "High", "Pending",
					"While Testing I find the bugs in you code")));
		} catch (ServiceException e) {
			System.out.println("Failed To Create Ticket");
			fail();
		}
	}

	@Test
	public void TestCreateTicketServiceFail() {
		TicketService ticketService = new TicketService();
		assertThrows(ServiceException.class, () -> {
			ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com",
					"gowtham.sathyamoorthy@fssa.freshworks.com", "I have find bugs in your code", "Pending", "High",
					"While Testing I find the bugs in your code"));
		});
	}
}

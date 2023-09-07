package com.fssa.pupdesk.services;

import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TestTicketService {
	@Test
	void testCreateTicketServicePass() {
		TicketService ticketService = new TicketService();
		try {
			assertTrue(ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com",
					"gowtham.sathyamoorthy@fssa.freshworks.com", "I have a find bugs in your code", "High", "Pending",
					"While Testing I find the bugs in you code")));
		} catch (ServiceException e) {
			System.out.println("Failed To Create Ticket");
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testCreateTicketServiceFail() {
		TicketService ticketService = new TicketService();
		assertThrows(ServiceException.class, () -> {
			ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com",
					"gowtham.sathyamoorthy@fssa.freshworks.com", "I have find bugs in your code", "Pending", "High",
					"While Testing I find the bugs in your code"));
		});
	}

	@Test
	void testListTicketServicePass() {

		try {
			TicketService service = new TicketService();
			assertEquals(new TicketService().listTicketService("bhirahatees.periysamy@fssa.freshworks.com").size() > 0,
					true);
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test
	void testListTicketServiceFail() {

		TicketService service = new TicketService();
		assertThrows(ServiceException.class, () -> service.listTicketService("invalid.email@fssa.freshworks.com"));

	}

	@Test
	void testUpdateTicketServicePass() throws ServiceException {
		TicketService ticket = new TicketService();
		assertTrue(ticket.updateTicketStatusService("6C391430D0ADA6",
				"Sorry for the issue Bhirahatees We'll fix that issue for you \n , Thank You For reporting to us"));
	}

	@Test
	void testUpdateTicketServiceFail() {
		TicketService ticket = new TicketService();
		assertThrows(ServiceException.class, () -> ticket.updateTicketStatusService("A9B01DC0D11#$%8", ""));
	}

	@Test
	void testGetTicketServicePass() {

		TicketService service = new TicketService();
		try {

			assertNotNull(service.getTicketbyService("bhirahatees.periysamy@fssa.freshworks.com", "Closed"));
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test
	void testGetTicketServiceFail() {

		TicketService service = new TicketService();
		assertThrows(ServiceException.class, () -> {
			service.getTicketbyService("invalid.email@fssa.freshworks.com", "complete");
		});
	}
    @Test
	void testGetTicketByIdServicePass() {
		try {
			assertNotNull(new TicketService().getTicketByIdService("6C391430D0ADA6"));
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
    @Test
	void testGetTicketByIdServiceFail() {
		assertThrows(ServiceException.class, () -> new TicketService().getTicketByIdService("6C391430M0ADA6"));

	}

}

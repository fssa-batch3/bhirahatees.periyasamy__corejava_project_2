package pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pupdesk.dao.TicketDAO;
import pupdesk.dao.exceptions.DAOException;
import pupdesk.services.exceptions.ServiceException;

public class TestListTicketService {
	@Test
	void testListTicketServicePass() {
		TicketDAO tickets = new TicketDAO();
		try {
			TicketService service = new TicketService();

			assertTrue(service.listTicketService(tickets.listTickets("bhirahatees.periysamay@fssa.freshworks.com")));
		} catch (ServiceException | DAOException e) {

		}
	}

	@Test
	void testListTicketServiceFail() {
		TicketDAO tickets = new TicketDAO();
		TicketService service = new TicketService();
		try {

			System.out.println(
					service.listTicketService(tickets.listTickets("invalid.email@fssa.freshworks.com")));
		} catch (ServiceException | DAOException e) {
			assertAll(() -> {
				assertThrows(ServiceException.class, () -> {
					service.listTicketService(tickets.listTickets("invalid.email@fssa.freshworks.com"));
				});
				assertThrows(DAOException.class, () -> {
					service.listTicketService(tickets.listTickets("invalid.email@fssa.freshworks.com"));
				});
			});
		}
	}
}

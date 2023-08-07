package pupdesk.services;

import pupdesk.DAO.TicketDAO;
import pupdesk.DAO.exceptions.DAOException;
import pupdesk.model.Ticket;
import pupdesk.model.User;
import pupdesk.services.exceptions.ServiceException;
import pupdesk.validation.TicketValidator;
import pupdesk.validation.UserValidator;
import pupdesk.validation.exceptions.InvalidTicketException;

public class TicketService {
	public boolean createTicketService(Ticket ticket) throws ServiceException {
		TicketDAO TicketDAO = new TicketDAO();
		try {
			TicketValidator.validateTicket(ticket);
			if (TicketDAO.createTicket(ticket)) {
				System.out.println("Ticket Created Successfully in this Id [" + ticket.getTicketId() + "]");
				return true;
			} else {

				return false;
			}

		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to create the Ticket");
		}

	}

	public static void main(String[] args) {
		try {
			System.out.println(
					new TicketService().createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com",
							"gowtham.sathyamoorthy@fssa.freshworks.com", "I have a find bugs in your code", "High",
							"Pending", "While Testing I find the bugs in you code")));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

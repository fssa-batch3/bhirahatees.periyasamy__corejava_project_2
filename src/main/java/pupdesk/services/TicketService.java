package pupdesk.services;

import java.util.List;

import pupdesk.dao.TicketDAO;
import pupdesk.dao.exceptions.DAOException;
import pupdesk.model.Ticket;
import pupdesk.services.exceptions.ServiceException;
import pupdesk.validation.TicketValidator;
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

	public boolean listTicketService(List<Ticket> list) throws ServiceException {
		if (list.size() == 0)
			throw new ServiceException("There is no tickets");
		for (Ticket ticket : list) {
			try {
				if (new TicketValidator().validateTicket(ticket)) {
					continue;
				}
			} catch (InvalidTicketException e) {
				throw new ServiceException("Invalid Tickets");
			}
		}
		return true;
	}

}

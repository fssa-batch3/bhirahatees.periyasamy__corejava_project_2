package com.fssa.pupdesk.services;

import com.fssa.pupdesk.dao.TicketDAO;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import com.fssa.pupdesk.validation.TicketValidator;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

import java.util.List;

public class TicketService {
	public boolean createTicketService(Ticket ticket) throws ServiceException {
		TicketDAO ticketDAO = new TicketDAO();
		try {
			TicketValidator.validateTicket(ticket);
				System.out.println("Ticket Created Successfully in this Id [" + ticket.getTicketId() + "]");
				return ticketDAO.createTicket(ticket);
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to create the Ticket",e);
		}

	}

	public boolean listTicketService(String email) throws ServiceException {
		List<Ticket> list;
		try {
			list = new TicketDAO().listTickets(email);
		} catch (DAOException e) {
			throw new ServiceException("Faile to get list of tickets");
		}
		if (list.isEmpty())
			throw new ServiceException("There is no tickets");
		for (Ticket ticket : list) {
			try {
				if (!TicketValidator.validateTicket(ticket)) {
					throw new ServiceException("InvalidTickets");
				}
			} catch (InvalidTicketException e) {
				throw new ServiceException("Invalid Tickets");
			}
		}
		return true;
	}

	public boolean updateTicketStatusService(String ticketId) throws ServiceException {
		TicketDAO ticket = new TicketDAO();
		boolean isUpdated = false;
		try {
			if (TicketValidator.validateTicketId(ticketId)) {
				isUpdated = ticket.updateTicketStatus(ticketId);
			} else {
				throw new ServiceException("Invalid Ticket Id");
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed To Update the Ticket");
		}
		return isUpdated;
	}

	public boolean getTicketbyService(String email, String status) throws ServiceException {
		try {
			List<Ticket> tickets = new TicketDAO().getTickets(email, status);
			if (tickets.isEmpty()) {
				throw new ServiceException("There is no tickets");
			} else {
				for (Ticket ticket : tickets) {

					if (!TicketValidator.validateTicket(ticket)) {
						throw new ServiceException("Failed to validate Tickets");
					}

				}
				return true;
			}
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to get Tickets in service");
		}

	}
}

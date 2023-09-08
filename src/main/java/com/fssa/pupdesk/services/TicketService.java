package com.fssa.pupdesk.services;

import com.fssa.pupdesk.dao.TicketDAO;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import com.fssa.pupdesk.validation.TicketValidator;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

import java.util.List;

/**
 * Service class for managing ticket-related operations.
 */
public class TicketService {

	/**
	 * Creates a new ticket.
	 *
	 * @param ticket The ticket to be created.
	 * @return true if the ticket is created successfully, false otherwise.
	 * @throws ServiceException If there is an issue creating the ticket.
	 */
	public boolean createTicketService(Ticket ticket) throws ServiceException {
		TicketDAO ticketDAO = new TicketDAO();
		try {
			TicketValidator.validateTicket(ticket);
			return ticketDAO.createTicket(ticket);
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Lists tickets for a specific user based on their email.
	 *
	 * @param email The email of the user to list tickets for.
	 * @return A list of tickets for the specified user.
	 * @throws ServiceException If there is an issue retrieving the list of tickets.
	 */
	public List<Ticket> listTicketService(String email) throws ServiceException {
		List<Ticket> list;
		try {
			list = new TicketDAO().listTickets(email);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		if (list.isEmpty())
			throw new ServiceException("There are no tickets");
		for (Ticket ticket : list) {
			try {
				if (!TicketValidator.validateTicket(ticket)) {
					throw new ServiceException("Invalid Tickets");
				}
			} catch (InvalidTicketException e) {
				throw new ServiceException(e.getMessage());
			}
		}
		return list;
	}

	/**
	 * Updates the status and closing description of a ticket.
	 *
	 * @param ticketId           The ID of the ticket to update.
	 * @param closingDescription The closing description for the ticket.
	 * @return true if the ticket status is updated successfully, false otherwise.
	 * @throws ServiceException If there is an issue updating the ticket status.
	 */
	public boolean updateTicketStatusService(String ticketId, String closingDescription) throws ServiceException {
		TicketDAO ticket = new TicketDAO();
		boolean isUpdated = false;
		try {
			if (TicketValidator.validateTicketId(ticketId)) {
				isUpdated = ticket.updateTicketStatus(ticketId, closingDescription);
			} else {
				throw new ServiceException("Invalid Ticket Id");
			}
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
		return isUpdated;
	}

	/**
	 * Gets tickets for a specific user with a specified status.
	 *
	 * @param email  The email of the user to get tickets for.
	 * @param status The status of the tickets to retrieve.
	 * @return A list of tickets with the specified status for the user.
	 * @throws ServiceException If there is an issue retrieving the tickets.
	 */
	public List<Ticket> getTicketbyService(String email, String status) throws ServiceException {
		try {
			List<Ticket> tickets = new TicketDAO().getTickets(email, status);
			if (tickets.isEmpty()) {
				throw new ServiceException("There are no tickets");
			} else {
				for (Ticket ticket : tickets) {
					if (!TicketValidator.validateTicket(ticket)) {
						throw new ServiceException("Failed to validate Tickets");
					}
				}
				return tickets;
			}
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Gets a ticket by its ID.
	 *
	 * @param ticketId The ID of the ticket to retrieve.
	 * @return The ticket with the specified ID.
	 * @throws ServiceException If there is an issue retrieving the ticket.
	 */
	public Ticket getTicketByIdService(String ticketId) throws ServiceException {
		try {
			Ticket ticket = new TicketDAO().getTicketById(ticketId);
			new TicketValidator().validateTicket(ticket);
			return ticket;
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

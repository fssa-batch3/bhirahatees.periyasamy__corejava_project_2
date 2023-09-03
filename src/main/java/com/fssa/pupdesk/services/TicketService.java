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
			return ticketDAO.createTicket(ticket);
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to create the Ticket", e);
		}

	}

	public List<Ticket> listTicketService(String email) throws ServiceException {
		List<Ticket> list;
		try {
			list = new TicketDAO().listTickets(email);
		} catch (DAOException e) {
			throw new ServiceException("Failed to get list of tickets");
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
		return list;
	}

	public boolean updateTicketStatusService(String ticketId, String ClosingDescription) throws ServiceException {
		TicketDAO ticket = new TicketDAO();
		boolean isUpdated = false;
		try {
			if (TicketValidator.validateTicketId(ticketId)) {
				isUpdated = ticket.updateTicketStatus(ticketId, ClosingDescription);
			} else {
				throw new ServiceException("Invalid Ticket Id");
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed To Update the Ticket");
		}
		return isUpdated;
	}

	public List<Ticket> getTicketbyService(String email, String status) throws ServiceException {
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
				return tickets;
			}
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to get Tickets in service");
		}

	}

	public Ticket getTicketByIdService(String ticketId) throws ServiceException {
		try {
			Ticket ticket = new TicketDAO().getTicketById(ticketId);
			System.out.println("1"+ticket.toString());
			new TicketValidator().validateTicket(ticket);
			return ticket;
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException("Failed to get a Ticket");
		}
	}
	
	public static void main(String[] args) {
		try {
			Ticket ticket = new TicketService().getTicketByIdService("6C391430D0ADA6");
			System.out.println(ticket.toString());
		} catch (ServiceException e) {
			System.out.println("Failed");
		}
		
	}
}

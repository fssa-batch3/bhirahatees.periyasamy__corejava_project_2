package com.fssa.pupdesk.services;

import com.fssa.pupdesk.dao.TicketDAO;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import com.fssa.pupdesk.validation.TicketValidator;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

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

    public boolean listTicketService(String email) throws ServiceException {
        List<Ticket> list;
        try {
            list = new TicketDAO().listTickets(email);
        }catch(DAOException e) {
          throw new ServiceException("Faile to get list of tickets");
        }
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

    public boolean updateTicketStatusService(String ticketId) throws ServiceException {
        TicketDAO ticket = new TicketDAO();
        boolean isUpdated = false;
        try {
            if (TicketValidator.validateTicketId(ticketId)) {
                isUpdated = ticket.updateTicketStatus(ticketId);
            }else {
				throw new ServiceException("Invalid Ticket Id");
			}
        } catch (DAOException e) {
			System.out.print("Exception happened");
            throw new ServiceException("Failed To Update the Ticket");
        }
        return isUpdated;
    }

    public boolean getTicketbyService(String email , String status) throws ServiceException{
        try{
            ArrayList<Ticket> tickets = new TicketDAO().getTickets(email, status);
            if(tickets.size() == 0){
                throw new ServiceException("There is no tickets");
            } else {
                for (Ticket ticket : tickets) {
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
        }catch(DAOException e){
              throw new ServiceException("Failed to get Tickets in service");
        }

    }

}

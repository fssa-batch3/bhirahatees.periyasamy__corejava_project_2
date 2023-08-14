package com.fssa.pupdesk.services;


import com.fssa.pupdesk.model.Ticket;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicketService {
    @Test
    public void TestCreateTicketServicePass() {
        TicketService ticketService = new TicketService();
        try {
            assertTrue(ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com", "gowtham.sathyamoorthy@fssa.freshworks.com", "I have a find bugs in your code", "High", "Pending", "While Testing I find the bugs in you code")));
        } catch (ServiceException e) {
            System.out.println("Failed To Create Ticket");
            fail();
        }
    }

    @Test
    public void TestCreateTicketServiceFail() {
        TicketService ticketService = new TicketService();
        assertThrows(ServiceException.class, () -> {
            ticketService.createTicketService(new Ticket("bhirahatees.periysamy@fssa.freshworks.com", "gowtham.sathyamoorthy@fssa.freshworks.com", "I have find bugs in your code", "Pending", "High", "While Testing I find the bugs in your code"));
        });
    }

    @Test
    void testListTicketServicePass() {

        try {
            TicketService service = new TicketService();

            assertTrue(service.listTicketService("bhirahatees.periysamay@fssa.freshworks.com"));
        } catch (ServiceException e) {
              fail();
        }
    }

    @Test
    void testListTicketServiceFail() {

        TicketService service = new TicketService();
        try {

            System.out.println(service.listTicketService("invalid.email@fssa.freshworks.com"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    void TestUpdateTicketServicePass() throws ServiceException {
        TicketService ticket = new TicketService();
        assertTrue(ticket.updateTicketStatusService("A9B01DC0D11938"));
    }

    @Test
    void TestUpdateTicketServiceFail()  {
        TicketService ticket = new TicketService();
        assertThrows(ServiceException.class, () -> ticket.updateTicketStatusService("A9B01DC0D11#$%8"));
    }

    @Test
    void testGetTicketServicePass() {

        TicketService service = new TicketService();
        try {

            assertTrue(service.getTicketbyService("bhirahatees.periysamy@fssa.freshworks.com","Closed"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    void testGetTicketServiceFail() {

        TicketService service = new TicketService();
       assertThrows(ServiceException.class,()->{
           service.getTicketbyService("invalid.email@fssa.freshworks.com","complete");
       });
    }

}

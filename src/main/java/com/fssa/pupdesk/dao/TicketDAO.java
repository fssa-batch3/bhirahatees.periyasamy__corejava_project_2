package com.fssa.pupdesk.dao;

import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    UserDAO dbConnection = new UserDAO();

    public boolean createTicket(Ticket ticket) throws DAOException {
        String insertQuery = "INSERT INTO tickets (fromEmail , toEmail , summary , ticketId , priority , status,description,createdate) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = dbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(insertQuery)){
            // Prepare SQL statement
            statement.setString(1, ticket.getFrom());
            statement.setString(2, ticket.getTo());
            statement.setString(3, ticket.getSummary());
            statement.setString(4, ticket.getTicketId());
            statement.setString(5, ticket.getPriority());
            statement.setString(6, ticket.getStatus());
            statement.setString(7, ticket.getDescription());
            statement.setString(8, ticket.getCreatedTime());
            
            // Execute the query
            int rows = statement.executeUpdate();
         
            // Return successful or not
            return rows == 1;
        } catch (SQLException e) {
           
            throw new DAOException("Failed to Create the ticket",e);
        }

    }

    public List<Ticket> listTickets(String email) throws DAOException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        String selectQuery = "SELECT * FROM tickets WHERE fromEmail = ? OR toEmail = ? OR toEmail = NULL";
        try ( Connection connection = dbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(selectQuery)){
            statement.setString(1, email);
            statement.setString(2, email);
            ResultSet resultData = statement.executeQuery();
            while (resultData.next()) {
            	Ticket ticket = new Ticket();
                ticket.setCreatedTime(resultData.getString("createdate"));
                ticket.setDescription(resultData.getString("description"));
                ticket.setFrom(resultData.getString("fromEmail"));
                ticket.setPriority(resultData.getString("priority"));
                ticket.setStatus(resultData.getString("status"));
                ticket.setSummary(resultData.getString("summary"));
                ticket.setTicketId(resultData.getString("ticketId"));
                ticket.setTo(resultData.getString("toEmail"));
                tickets.add(ticket);
            }
      
            return tickets;
        } catch (SQLException e) {
            throw new DAOException("Failed to get Tickets");
        }

    }

    public boolean updateTicketStatus(String ticketId) throws DAOException {
        String updateQuery = "UPDATE tickets SET status = ? WHERE ticketid = ? ";
        try ( Connection connect = dbConnection.getConnection();PreparedStatement statement = connect.prepareStatement(updateQuery)){
            statement.setString(1, "Closed");
            statement.setString(2, ticketId);
            int row = statement.executeUpdate();
            return row == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Failed To Update");
        }
    }

    public List<Ticket> getTickets(String email , String status)throws DAOException{
      String selectQuery = "SELECT * FROM tickets WHERE toEmail = ? OR fromEmail =? OR status = ?";
      ArrayList<Ticket> tickets = new ArrayList<>();
      try(  Connection connect = dbConnection.getConnection(); PreparedStatement statment = connect.prepareStatement(selectQuery))
      {
       statment.setString(1,email);
       statment.setString(2,email);
       statment.setString(3,status);
       ResultSet resultData = statment.executeQuery();
       while(resultData.next()){
           tickets.add(new Ticket(resultData.getString("fromEmail"), resultData.getString("toEmail"),
                   resultData.getString("summary"), resultData.getString("ticketId"),
                   resultData.getString("priority"),
                   resultData.getString("status"), resultData.getString("description"), resultData.getString("createdate")));
       }
      }catch (SQLException e){
          e.printStackTrace();
          throw new DAOException("Failed to Get Tickets");
      }
      return tickets;
    }




}

package com.fssa.pupdesk.dao;

import com.fssa.pupdesk.utils.ConnectionUtil;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TicketDAO {
	ConnectionUtil dbConnection = new ConnectionUtil();

	public static List<Ticket> extractDataFromResultSet(ResultSet resultData) throws SQLException {
		ArrayList<Ticket> tickets = new ArrayList<>();
		while (resultData.next()) {
		Ticket ticket = new Ticket();
		ticket.setCreatedTime(resultData.getString("created_at"));
		ticket.setDescription(resultData.getString("description"));
		ticket.setFrom(resultData.getString("from_email"));
		ticket.setPriority(resultData.getString("priority"));
		ticket.setStatus(resultData.getString("status"));
		ticket.setSummary(resultData.getString("summary"));
		ticket.setTicketId(resultData.getString("ticket_id"));
		ticket.setTo(resultData.getString("to_email"));
		ticket.setClosingDescription(resultData.getString("closing_description"));
		tickets.add(ticket);
		}
		return tickets;
	}

	public boolean createTicket(Ticket ticket) throws DAOException {
		String insertQuery = "INSERT INTO tickets (from_email , to_email , summary , ticket_id , priority , status,description,created_at) VALUES(?,?,?,?,?,?,?,?)";
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertQuery)) {
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

			throw new DAOException("Failed to Create the ticket", e);
		}

	}

	public List<Ticket> listTickets(String email) throws DAOException {
		String selectQuery = "SELECT * FROM tickets WHERE from_email = ? OR to_email = ? OR to_email = NULL";
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectQuery)) {
			statement.setString(1, email);
			statement.setString(2, email);
			ResultSet resultData = statement.executeQuery();
			return extractDataFromResultSet(resultData);
		} catch (SQLException e) {
			throw new DAOException("Failed to get Tickets");
		}

	}

	public boolean updateTicketStatus(String ticketId , String closingDescription) throws DAOException {
		String updateQuery = "UPDATE tickets SET status = ? , closing_description = ? WHERE ticket_id = ? ";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(updateQuery)) {
			statement.setString(1, "Closed");
			statement.setString(2, closingDescription);
			statement.setString(3, ticketId);
			int row = statement.executeUpdate();
			return row == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed To Update");
		}
	}

	public List<Ticket> getTickets(String email, String status) throws DAOException {
		String selectQuery = "SELECT * FROM tickets WHERE to_email = ? AND from_email =? OR status = ?";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statment = connect.prepareStatement(selectQuery)) {
			statment.setString(1, email);
			statment.setString(2, email);
			statment.setString(3, status);
			ResultSet resultData = statment.executeQuery();
		    return extractDataFromResultSet(resultData);
		} catch (SQLException e) {
			throw new DAOException("Failed to Get Tickets");
		}
	}
	
	public Ticket getTicketById(String ticketId) throws DAOException {
		Ticket ticket = new Ticket();
		String selectQuery = "SELECT * FROM tickets WHERE ticket_id = ?";
		try(Connection connect = dbConnection.getConnection();PreparedStatement statment = connect.prepareStatement(selectQuery)){
			statment.setString(1,ticketId);
			ResultSet resultData = statment.executeQuery();
			while (resultData.next()) {
				ticket.setCreatedTime(resultData.getString("created_at"));
				ticket.setDescription(resultData.getString("description"));
				ticket.setFrom(resultData.getString("from_email"));
				ticket.setPriority(resultData.getString("priority"));
				ticket.setStatus(resultData.getString("status"));
				ticket.setSummary(resultData.getString("summary"));
				ticket.setTicketId(resultData.getString("ticket_id"));
				ticket.setTo(resultData.getString("to_email"));
				ticket.setClosingDescription(resultData.getString("closing_description"));
				}
			return ticket;
		}catch(SQLException e) {
			throw new DAOException("Failed to get Tickets");
		}
	}
	
}

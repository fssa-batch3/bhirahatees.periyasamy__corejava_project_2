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

	/**
	 * Extracts ticket data from a ResultSet and returns a list of Ticket objects.
	 *
	 * @param resultData The ResultSet containing ticket data.
	 * @return A List of Ticket objects.
	 * @throws SQLException If there is an error while processing the ResultSet.
	 */
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

	/**
	 * Creates a new ticket in the database.
	 *
	 * @param ticket The Ticket object representing the ticket to create.
	 * @return True if the ticket was successfully created, false otherwise.
	 * @throws DAOException If there is an error while accessing the database.
	 */
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
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of tickets associated with a specific user's email address.
	 *
	 * @param email The email address of the user to retrieve tickets for.
	 * @return A List of Ticket objects representing the user's tickets.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public List<Ticket> listTickets(String email) throws DAOException {
		String selectQuery = "SELECT * FROM tickets WHERE from_email = ? OR to_email = ? OR to_email IS NULL";
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

	/**
	 * Updates the status and closing description of a ticket in the database.
	 *
	 * @param ticketId           The ID of the ticket to update.
	 * @param closingDescription The closing description for the ticket.
	 * @return True if the ticket was successfully updated, false otherwise.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public boolean updateTicketStatus(String ticketId, String closingDescription) throws DAOException {
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

	/**
	 * Retrieves a list of tickets associated with a specific user's email address
	 * and status.
	 *
	 * @param email  The email address of the user to retrieve tickets for.
	 * @param status The status of the tickets to retrieve (e.g., "Open" or
	 *               "Closed").
	 * @return A List of Ticket objects representing the user's tickets with the
	 *         specified status.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public List<Ticket> getTickets(String email, String status) throws DAOException {
		String selectQuery = "SELECT * FROM tickets WHERE (to_email = ? OR from_email = ?) AND status = ?";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(selectQuery)) {
			statement.setString(1, email);
			statement.setString(2, email);
			statement.setString(3, status);
			ResultSet resultData = statement.executeQuery();
			return extractDataFromResultSet(resultData);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Retrieves a ticket by its unique ticket ID.
	 *
	 * @param ticketId The unique ID of the ticket to retrieve.
	 * @return A Ticket object representing the ticket with the specified ID.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public Ticket getTicketById(String ticketId) throws DAOException {
		String selectQuery = "SELECT * FROM tickets WHERE ticket_id = ?";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(selectQuery)) {
			statement.setString(1, ticketId);
			ResultSet resultData = statement.executeQuery();
			return extractDataFromResultSet(resultData).get(0);
		} catch (SQLException e) {
			throw new DAOException("Failed to get Tickets");
		}
	}

}

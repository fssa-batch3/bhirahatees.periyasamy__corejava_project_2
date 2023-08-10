package pupdesk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pupdesk.dao.exceptions.DAOException;
import pupdesk.model.Ticket;

public class TicketDAO {
	UserDAO dbConnection = new UserDAO();

	public boolean createTicket(Ticket ticket) throws DAOException {

		try {
			Connection connection = dbConnection.getConnection();
			// Prepare SQL statement
			String insertQuery = "INSERT INTO tickets (fromEmail , toEmail , summary , ticketId , priority , status,description,createdate) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, ticket.getFrom());
			statement.setString(2, ticket.getTo());
			statement.setString(3, ticket.getSummary());
			statement.setString(4, ticket.getTicketId());
			statement.setString(5, ticket.getPriority());
			statement.setString(6, ticket.getStatus());
			statement.setString(7, ticket.getDescription());
			statement.setString(8, ticket.getCreateTime());
			// Execute the query
			int rows = statement.executeUpdate();

			statement.close();
			connection.close();
			// Return successful or not
			return rows == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to Create the ticket");
		}

	}

	public List<Ticket> listTickets(String email) throws DAOException {
		ArrayList<Ticket> tickets = new ArrayList<>();
		String selectQuery = "SELECT * FROM tickets WHERE fromEmail = ? OR toEmail = ? OR toEmail = NULL";
		try {
			Connection connection = dbConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			statement.setString(1, email);
			statement.setString(2, email);
			ResultSet resultData = statement.executeQuery();
			while (resultData.next()) {
				tickets.add(new Ticket(resultData.getString("fromEmail"), resultData.getString("toEmail"),
						resultData.getString("summary"), resultData.getString("ticketId"),
						 resultData.getString("priority"),
						resultData.getString("status"), resultData.getString("description"),resultData.getString("createdate")));
			}
			connection.close();
			resultData.close();
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to get Tickets");
		}

	}

	public static void main(String[] args) {
		try {
			List<Ticket> tickets = new TicketDAO().listTickets("bhirahatees.periysamy@fssa.freshworks.com");
			for (Ticket ticket : tickets) {
				System.out.println(ticket.toString());
			}
		} catch (DAOException e) {

			e.printStackTrace();
		}
		;
	}

}

package pupdesk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pupdesk.DAO.exceptions.DAOException;
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

			// Return successful or not
			return rows == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Failed to Create the ticket");
		}

	}

	public static void main(String[] args) {
		try {
			System.out.println(new TicketDAO().createTicket(new Ticket("settu@gmail.com", "bhirahatees@gmail.com",
					"Free Time", "High", "On Progress", "When Will you free")));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}

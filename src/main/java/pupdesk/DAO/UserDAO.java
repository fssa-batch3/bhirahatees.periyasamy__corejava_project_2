package pupdesk.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import pupdesk.DAO.exceptions.DAOException;
import pupdesk.model.User;

public class UserDAO {

	// Connect to database
	public Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/project";
		Connection connection = DriverManager.getConnection(url, "root", "12345678");
		return connection;

	}

	// Get user from DB - Login
	public User login(String email, String password) throws DAOException {
		try {
			User user = null;
			Connection connection = getConnection();
			String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
						rs.getNString("teamcode"), rs.getString("password"));
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("Failed to Login");
		}

	}

	public boolean createUser(User user) throws DAOException {

		try {
			// Get connection
			Connection connection = getConnection();

			// Prepare SQL statement
			String insertQuery = "INSERT INTO users (firstname, lastname, email, teamcode, password)VALUES(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getTeamCode());
			statement.setString(5, user.getPassword());

			// Execute the query
			int rows = statement.executeUpdate();

			// Return successful or not
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed to register User");
		}
	}

//	Updating User from Email 
	public User updateUser(String where, String which, String data) throws DAOException {
		User user = null;
		try {
			Connection connection = getConnection();
			String updateQuery = "UPDATE users SET " + which + "= ? WHERE email = ?";
			PreparedStatement statment = connection.prepareStatement(updateQuery);
			statment.setString(1, data);
			statment.setString(2, where);

			int rows = statment.executeUpdate();

			String selectQuery = "SELECT * FROM users WHERE email = ?";
			PreparedStatement newStatment = connection.prepareStatement(selectQuery);
			newStatment.setString(1, where);
			ResultSet rs = newStatment.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),
						rs.getNString("teamcode"), rs.getString("password"));
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("Update Failed");
		}
	}
	
	
//    Deletin the user from the table   
	public boolean deleteUser(String email) throws DAOException{
		try {
			Connection connection = getConnection();
			String deleteQuery = "DELETE FROM users WHERE email =?";
			PreparedStatement statment = connection.prepareStatement(deleteQuery);
			statment.setString(1,email);
			int rows = statment.executeUpdate();
			return rows == 1;
		}catch(SQLException e) {
			throw new DAOException("Failed to Delete");
		}
	}
	


}

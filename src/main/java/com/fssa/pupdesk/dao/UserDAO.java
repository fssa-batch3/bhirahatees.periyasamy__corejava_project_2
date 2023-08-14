package com.fssa.pupdesk.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.User;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.ResultSet;

public class UserDAO {

	// Connect to database
	public Connection getConnection() throws SQLException {

		String DB_URL;
		String DB_USER;
		String DB_PASSWORD;

		String LOCAL_DB_URL = "jdbc:mysql://localhost/project";
		String LOCAL_DB_USER = "root";
		String LOCAL_DB_PASSWORD = "12345678";

		if (System.getenv("CI") != null) {
			DB_URL = System.getenv("DB_URL");
			DB_USER = System.getenv("DB_USER");
			DB_PASSWORD = System.getenv("DB_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			DB_URL = env.get("DB_URL");
			DB_USER = env.get("DB_USER");
			DB_PASSWORD = env.get("DB_PASSWORD");
		}
		Connection connection = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USER, LOCAL_DB_PASSWORD);
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
			rs.close();
			statement.close();
			connection.close();
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
			connection.close();
			statement.close();
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
			connection.close();
			rs.close();
			statment.close();
			newStatment.close();
			return user;
		} catch (SQLException e) {
			throw new DAOException("Update Failed");
		}
	}

//    Deletin the user from the table   
	public boolean deleteUser(String email) throws DAOException {
		try {
			Connection connection = getConnection();
			String deleteQuery = "DELETE FROM users WHERE email =?";
			PreparedStatement statment = connection.prepareStatement(deleteQuery);
			statment.setString(1, email);
			int rows = statment.executeUpdate();
			statment.close();
			connection.close();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed to Delete");
		}
	}

}

package com.fssa.pupdesk.dao;

import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.utils.ConnectionUtil;

import java.sql.*;
import java.util.*;

public class UserDAO {

	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String EMAIL = "email";
	private static final String TEAMCODE = "teamcode";
	private static final String PASSWORD = "password";

	// Connect to database
	ConnectionUtil dbConnection = new ConnectionUtil();

	// Get user from DB - Login
	public User login(String email, String password) throws DAOException {
		String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
		User user = null;
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectQuery)) {
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(FIRSTNAME), rs.getString(LASTNAME), rs.getString(EMAIL),
						rs.getNString(TEAMCODE), rs.getString(PASSWORD));
				user.setProfileImageUrl(rs.getString("profile_image_url"));
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("Failed to Login");
		}

	}

	public boolean createUser(User user) throws DAOException {
		String insertQuery = "INSERT INTO users (firstname, lastname, email, teamcode, password,profile_image_url)VALUES(?,?,?,?,?,?)";

		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertQuery)) {
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getTeamCode());
			statement.setString(5, user.getPassword());
			statement.setString(6, "https://cdn-icons-png.flaticon.com/512/64/64572.png");
			// Execute the query
			int rows = statement.executeUpdate();
			// Return successful or not
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed to register User");
		}
	}

	// Updating User from Email
	public User updateUser(User user) throws DAOException {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(
						"UPDATE users SET firstname = ?,lastname = ? ,password = ? ,profile_image_url = ? WHERE email = ?");) {
			updateStatement.setString(1, user.getFirstname());
			updateStatement.setString(2, user.getLastname());
			updateStatement.setString(3, user.getPassword());
			updateStatement.setString(4, user.getProfileImageUrl());
			updateStatement.setString(5, user.getEmail());
			int rows = updateStatement.executeUpdate();
			if (rows > 1) {
				return null;
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("Failed to Update" + e.getMessage());
		}

	}

	// Deleting the user from the table
	public boolean deleteUser(String email) throws DAOException {
		String deleteQuery = "DELETE FROM users WHERE email =?";
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statment = connection.prepareStatement(deleteQuery)) {
			statment.setString(1, email);
			int rows = statment.executeUpdate();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<User> getSameTeamUsers(String email, String password) throws DAOException {
		User user = new UserDAO().login(email, password);
		ArrayList<User> members = new ArrayList<User>();
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE teamcode = ?")) {
			statement.setString(1, user.getTeamCode());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User userDetail = new User(rs.getString(FIRSTNAME), rs.getString(LASTNAME), rs.getString(EMAIL),
						rs.getString(TEAMCODE), rs.getString(PASSWORD));
				userDetail.setProfileImageUrl(rs.getString("profile_image_url"));
				members.add(userDetail);
			}
			return members;
		} catch (SQLException e) {
			throw new DAOException("Failed to get team");
		}
	}

}

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

	/**
	 * Retrieves a user from the database based on their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return A User object representing the retrieved user, or null if the user
	 *         does not exist.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public User login(String email) throws DAOException {
		// Method to retrieve a user based on their email address
		String selectQuery = "SELECT * FROM users WHERE email = ?";
		User user = null;
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectQuery)) {
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(FIRSTNAME), rs.getString(LASTNAME), rs.getString(EMAIL),
						rs.getNString(TEAMCODE), rs.getString(PASSWORD));
				user.setProfileImageUrl(rs.getString("profile_image_url"));
				user.setPassword(rs.getString(PASSWORD));
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("User Not Exists");
		}
	}

	/**
	 * Creates a new user in the database.
	 *
	 * @param user The User object representing the user to create.
	 * @return True if the user was successfully created, false otherwise.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public boolean createUser(User user) throws DAOException {
		// Method to create a new user in the database
		String insertQuery = "INSERT INTO users (firstname, lastname, email, teamcode, password, profile_image_url) VALUES(?,?,?,?,?,?)";

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
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Updates a user's information in the database based on their email address.
	 *
	 * @param user The User object representing the updated user information.
	 * @return The updated User object, or null if the update failed.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public User updateUser(User user) throws DAOException {
		// Method to update user information based on their email address
		final String query = "UPDATE users SET firstname = ?,lastname = ? ,password = ? ,profile_image_url = ? WHERE email = ?";
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(query);) {

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
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Deletes a user from the database based on their email address.
	 *
	 * @param email The email address of the user to delete.
	 * @return True if the user was successfully deleted, false otherwise.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public boolean deleteUser(String email) throws DAOException {
		// Method to delete a user based on their email address
		String deleteQuery = "DELETE FROM users WHERE email =?";
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
			statement.setString(1, email);
			int rows = statement.executeUpdate();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed to Delete User");
		}
	}

	/**
	 * Retrieves a list of users in the same team as the given user's email address.
	 *
	 * @param email The email address of the user to find team members for.
	 * @return A List of User objects representing the team members.
	 * @throws DAOException If there is an error while accessing the database.
	 */
	public List<User> getSameTeamUsers(String email) throws DAOException {
		User user = new UserDAO().login(email);
        System.out.println(user.toString());
		ArrayList<User> members = new ArrayList<User>();
		if (user == null) {
			return members;
		}
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE teamcode = ?")) {
			statement.setString(1, user.getTeamCode());
			System.out.println(user.toString()	);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User userDetail = new User(rs.getString(FIRSTNAME), rs.getString(LASTNAME), rs.getString(EMAIL),
						rs.getString(TEAMCODE),rs.getString(PASSWORD));
				userDetail.setProfileImageUrl(rs.getString("profile_image_url"));
				members.add(userDetail);
			}
			return members;
		} catch (SQLException e) {
			throw new DAOException("Failed to get Class Mates");
		}
	}

	public User getUser(String email) throws DAOException {
		// Method to retrieve a user based on their email address
		String selectQuery = "SELECT * FROM users WHERE email = ?";
		User user = null;
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectQuery)) {
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(FIRSTNAME), rs.getString(LASTNAME), rs.getString(EMAIL),
						rs.getNString(TEAMCODE),rs.getString(PASSWORD));
				user.setProfileImageUrl(rs.getString("profile_image_url"));

			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("User Not Exists");
		}
	}
	

}

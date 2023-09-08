package com.fssa.pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import com.fssa.pupdesk.dao.UserDAO;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import com.fssa.pupdesk.validation.UserValidator;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

/**
 * Service class for managing user-related operations.
 */
public class UserService {

	/**
	 * Registers a new user.
	 *
	 * @param user The user to be registered.
	 * @return true if the user is registered successfully, false otherwise.
	 * @throws ServiceException If there is an issue registering the user.
	 */
	public boolean registerUser(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			UserValidator.validateUser(user);
			return userDAO.createUser(user);
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Logs in a user with the provided email and password.
	 *
	 * @param email    The user's email.
	 * @param password The user's password.
	 * @return true if the login is successful, false otherwise.
	 * @throws ServiceException If there is an issue with the login process.
	 */
	public boolean loginUser(String email, String password) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			User user = user1.login(email);
			if (user.getPassword().equals(password)) {
				return UserValidator.validateUser(user);
			} else {
				throw new ServiceException("Password is Incorrect");
			}
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a user by their email and password.
	 *
	 * @param email    The user's email.
	 * @param password The user's password.
	 * @return The user with the specified email and password.
	 * @throws ServiceException If there is an issue retrieving the user.
	 */
	public User getUser(String email, String password) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			user = userDAO.login(email);
			UserValidator.validateUser(user);
			return user;
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates user information.
	 *
	 * @param user The updated user information.
	 * @return The updated user object.
	 * @throws ServiceException If there is an issue updating the user.
	 */
	public User updateUserService(User user) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			UserValidator.validateUser(user);
			return user1.updateUser(user);
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Deletes a user by their email.
	 *
	 * @param email The email of the user to be deleted.
	 * @return true if the user is deleted successfully, false otherwise.
	 * @throws ServiceException If there is an issue deleting the user.
	 */
	public boolean deleteUserService(String email) throws ServiceException {
		UserDAO user = new UserDAO();
		try {
			return user.deleteUser(email);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Gets a list of users in the same team as the specified user.
	 *
	 * @param email The email of the user to find teammates for.
	 * @return A list of users in the same team.
	 * @throws ServiceException If there is an issue retrieving the teammates.
	 */
	public List<User> getSameTeamUsersService(String email) throws ServiceException {
		UserDAO users = new UserDAO();
		try {
			List<User> teamMates = users.getSameTeamUsers(email);
			if (teamMates.isEmpty()) {
				throw new ServiceException("There are no Teammates");
			}
			for (User teamMate : teamMates) {
				if (!UserValidator.validateUser(teamMate)) {
					throw new ServiceException("Failed to get Teammates");
				}
			}
			return teamMates;
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}

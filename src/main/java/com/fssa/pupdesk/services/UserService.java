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

public class UserService {
	public boolean registerUser(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			UserValidator.validateUser(user);
			return userDAO.createUser(user);
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public boolean loginUser(String email, String password) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			return UserValidator.validateUser(user1.login(email, password));
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public User getUser(String email, String password) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			user = userDAO.login(email, password);
			UserValidator.validateUser(user);
			return user;
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public User updateUserService(User user) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			UserValidator.validateUser(user1.updateUser(user));
			return user;
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public boolean deleteUserService(String email) throws ServiceException {
		UserDAO user = new UserDAO();
		try {
			return user.deleteUser(email);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<User> getSameTeamUsersService(String email, String password) throws ServiceException {
		UserDAO users = new UserDAO();
		try {
			List<User> teamMates = users.getSameTeamUsers(email, password);
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
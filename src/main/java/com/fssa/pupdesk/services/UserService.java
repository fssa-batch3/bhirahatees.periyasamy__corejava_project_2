package com.fssa.pupdesk.services;

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
			throw new ServiceException("Not Valid User");
		}

	}

	public boolean loginUser(String email, String password) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			return UserValidator.validateUser(user1.login(email,password));
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException("Login Failed");
		}
	}

	public boolean updateUserService(String where, String which, String data) throws ServiceException {
		UserDAO user1 = new UserDAO();
		try {
			return UserValidator.validateUser(user1.updateUser(where, which, data));
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException("Update Failed");
		}
	}

	public boolean deleteUserService(String email) throws ServiceException {
		UserDAO user = new UserDAO();
		try {
			return user.deleteUser(email);
		} catch (DAOException e) {
			throw new ServiceException("Failed to Delete");
		}
	}

	public boolean getSameTeamUsersService(String email, String password) throws ServiceException {
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
		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException("Something Happened ! Failed to get Teammates");
		}

		return true;

	}

}
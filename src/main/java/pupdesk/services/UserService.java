package pupdesk.services;



import pupdesk.dao.UserDAO;
import pupdesk.dao.exceptions.DAOException;
import pupdesk.model.User;
import pupdesk.services.exceptions.ServiceException;
import pupdesk.validation.UserValidator;
import pupdesk.validation.exceptions.InvalidUserException;

public class UserService {
	public boolean registerUser(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			UserValidator.validateUser(user);
			if (userDAO.createUser(user)) {
				System.out.println(user.getFirstname() + " " + user.getLastname() + " Successfully registered!");
				return true;
			} else {
				return false;
			}

		} catch (DAOException | InvalidUserException e) {
			throw new ServiceException("Not Valid User");
		}

	}

	public boolean loginUser(String email, String password) throws ServiceException {
		UserDAO user1 = new UserDAO();
		User loginUser;
		try {
			loginUser = user1.login(email, password);
			if (loginUser == null) {
				return false;
			} else if (loginUser != null) {
				System.out.println(loginUser.getFirstname() + " " + loginUser.getLastname() + " Login Successfully !");
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException("Login Failed");
		}
		return false;
	}

	public boolean updateUserService(String where, String which, String data) throws ServiceException {
		UserDAO user1 = new UserDAO();
		User updateUser;
		try {
			updateUser = user1.updateUser(where, which, data);
			if (updateUser == null) {
				return false;
			} else if (updateUser != null) {
				System.out.print(updateUser.toString());
				System.out
						.println(updateUser.getFirstname() + " " + updateUser.getLastname() + " Update Successfully !");
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException("Update Failed");
		}
		return false;
	}

	public boolean deleteUserService(String email) throws ServiceException {
		UserDAO user = new UserDAO();
		try {
			if (user.deleteUser(email)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed to Delete");
		}
	}

}
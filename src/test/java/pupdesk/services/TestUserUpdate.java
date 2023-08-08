package pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import pupdesk.DAO.UserDAO;
import pupdesk.services.exceptions.ServiceException;

public class TestUserUpdate {
	@Test
	public void TestSuccesLogin() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.updateUserService("bhirahatees@fssa.freshworks.com", "firstname",
					"Pragathees");
			assertTrue(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
		}
	}

	@Test
	public void TestUpdateFail() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.updateUserService("bhirahatees.periysamy@gmail.com", "teamcode", "Pragathees");
			assertFalse(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
		}
	}
}

package com.fssa.pupdesk.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.dao.UserDAO;
import com.fssa.pupdesk.model.User;
import com.fssa.pupdesk.services.exceptions.ServiceException;

class TestUserService {

	@Test
	void testRegistrationSuccess() {
		UserService userService = new UserService();
		User user1 = new User("Gowtham", "Sathyamoorthy", "gowtham@freshworks.com", "H1IT5A", "Gowtham@123");
		System.out.print(user1.getTeamCode());
		try {
			assertTrue(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("Not Registerd");
			fail();
		}
	}

	@Test
	void testInvalidPassword() {

		UserService userService = new UserService();
		User user1 = new User("Bhirahatees", "Periyasamy", "bhirahatees.periyasamy@fssa,freshworks.com", "F03FEA",
				"varan123");
		try {
			assertFalse(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("Invalid Password");

		}
	}

	@Test
	void testSuccesLogin() {
		UserService userService = new UserService();
		try {
			boolean user = userService.loginUser("bhirahatees.periysamy@fssa.freshworks.com", "Bhirahatees@12345");
			System.out.println(user);
			assertTrue(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
			fail();
		}
	}

	@Test
	void testFailLogin() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.loginUser("Bhirahatees.periysamy@fssa.freshworks.com", "Bhirahaatees@123");
			System.out.println(user);
			assertFalse(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
		}
	}

	@Test
	void testUserNull() {

		UserService userService = new UserService();
		User user1 = null;
		try {
			assertFalse(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("User is Null");

		}

	}

	@Test
	void testUpdateSuccess() {

		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			User user = new User("Bhirahatees", "Periysamy", "bhirahatees.periysamy@fssa.freshworks.com", "IQU6A1",
					"Bhirahatees@12345");
			user.setProfileImageUrl("https://wallpaperaccess.com/full/664803.jpg");
			User updateService = userService.updateUserService(user);
			assertNotNull(updateService);
		} catch (ServiceException e) {
			System.out.println("Failed to Update");
			fail();
		}
	}

	@Test
	void testUpdateFail() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			User user = new User();
			assertNull(userService.updateUserService(user));
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
		}
	}

	@Test
	void testDeleteUserPass() {
		UserService user = new UserService();
		String delete = "karthik.pannerselvam@fssa.freshworks.com";
		try {
			assertTrue(user.deleteUserService(delete));
		} catch (ServiceException e) {
			System.out.println("Failed to Delete");
			fail();
		}
	}

	@Test
	void testDeleteUserFail() {
		UserService user = new UserService();
		String delete = "bhirahatees@fsa.freshworks.com";
		try {
			assertFalse(user.deleteUserService(delete));
		} catch (ServiceException e) {
			System.out.println("Failed to Delete");
		}
	}

	@Test
	void testGetSameTeamUsersServicePass() {
		try {
			assertNotNull(new UserService().getSameTeamUsersService("bhirahatees.periysamy@fssa.freshworks.com",
					"Bhirahatees@123"));
		} catch (ServiceException e) {

			fail();
		}
	}

	@Test
	void testGetSameTeamUsersServiceFail() {
		try {
			assertNull(new UserService().getSameTeamUsersService("settu@gmail.com", "Goafwtaam@123"));
		} catch (ServiceException e) {
			fail();
		}
	}
}

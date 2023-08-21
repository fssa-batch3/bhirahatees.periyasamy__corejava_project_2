package com.fssa.pupdesk.services;

import com.fssa.pupdesk.dao.UserDAO;
import com.fssa.pupdesk.model.User;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.services.exceptions.ServiceException;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserService {

	@Test
	public void testRegistrationSuccess() {
		UserService userService = new UserService();
		User user1 = new User("Gowtham", "Sathyamoorthy", "gowtham.sathayamoorthy@fssa.freshworks.com", "H1IT5A",
				"Gowtham@123");
		System.out.print(user1.getTeamCode());
		try {
			assertTrue(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("Not Registerd");
			fail();
		}
	}

	@Test
	public void testInvalidPassword() {

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
	public void testSuccesLogin() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.loginUser("bhirahatees@fssa.freshworks.com", "Bhirahatees@123");
			System.out.println(user);
			assertTrue(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
			fail();
		}
	}

	@Test
	public void testFailLogin() {
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
	public void testUserNull() {

		UserService userService = new UserService();
		User user1 = null;
		try {
			assertFalse(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("User is Null");

		}

	}

	@Test
	public void testUpdateSuccess() {

		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.updateUserService("bhirahatees@fssa.freshworks.com", "firstname", "Pragathees");
			assertTrue(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
			fail();
		}
	}

	@Test
	public void testUpdateFail() {
		UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();

		try {
			boolean user = userService.updateUserService("bhirahatees.periysamy@gmail.com", "teamcode", "Pragathees");
			assertFalse(user);
		} catch (ServiceException e) {
			System.out.print("Invalid Credentials");
		}
	}

	@Test
	public void testDeleteUserPass() {
		UserService user = new UserService();
		String delete = "bhirahatees.periyasamy@fssa.freshworks.com";
		try {
			assertTrue(user.deleteUserService(delete));
		} catch (ServiceException e) {
			System.out.println("Failed to Delete");
			fail();
		}
	}

	@Test
	public void testDeleteUserFail() {
		UserService user = new UserService();
		String delete = "bhirahatees@fsa.freshworks.com";
		try {
			assertFalse(user.deleteUserService(delete));
		} catch (ServiceException e) {
			System.out.println("Failed to Delete");
		}
	}

	@Test
	public void testGetSameTeamUsersServicePass() {
		try {
			assertTrue(new UserService().getSameTeamUsersService("settu@gmail.com", "Gowtham@123"));
		} catch (ServiceException e) {

			fail();
		}
	}
	@Test
	public void testGetSameTeamUsersServiceFail() {
			assertThrows(ServiceException.class, ()-> new UserService().getSameTeamUsersService("settu@gmail.com", "Goafwtaam@123"));
	}
}

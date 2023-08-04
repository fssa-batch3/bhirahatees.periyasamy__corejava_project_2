package pupdesk.services;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pupdesk.DAO.*;
import pupdesk.model.User;
import pupdesk.services.UserService;
import pupdesk.services.exceptions.ServiceException;

public class TestRegisterFeature {


	@Test
	public void testRegistrationSuccess() {
		UserService userService = new UserService();
		User user1 = new User("Gowtham","Sathyamoorthy", "gowtham.sathayamoorthy@fssa.freshworks.com","H1IT5A", "Gowtham@123");
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
		User user1 = new User("Bhirahatees", "Periyasamy", "bhirahatees.periyasamy@fssa,freshworks.com","F03FEA","varan123");
		try {
			assertFalse(userService.registerUser(user1));
		} catch (ServiceException e) {
			System.out.println("Invalid Password");

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

}

package pupdesk.services;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pupdesk.services.exceptions.ServiceException;

public class TestDeleteUser {
	@Test
	public void TestDeleteUserPass() {
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
	public void TestDeleteUserFail() {
		UserService user = new UserService();
		String delete = "bhirahatees@fsa.freshworks.com";
		try {
			assertFalse(user.deleteUserService(delete));
		} catch (ServiceException e) {
			System.out.println("Failed to Delete");
		}
	}
}

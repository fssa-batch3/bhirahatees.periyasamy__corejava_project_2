package pupdesk.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import pupdesk.model.User;
import pupdesk.DAO.UserDAO;
import pupdesk.DAO.exceptions.DAOException;
import pupdesk.services.exceptions.ServiceException;

public class TestLoginUser {
  @Test
  public void TestSuccesLogin() {
	  UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();
	
		try {
			boolean user = userService.loginUser("bhirahatees.periyasamy@fssa.freshworks.com","Bhirahatees@123");
			System.out.println(user);
			assertTrue(user);
		} catch (ServiceException e) {
		  System.out.print("Invalid Credentials");
		}
  }
  
  @Test
  public void TestFailLogin() {
	  UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();
	
		try {
			boolean user = userService.loginUser("Bhirahatees.periysamy@fssa.freshworks.com","Bhirahaatees@123");
			System.out.println(user);
			assertFalse(user);
		} catch (ServiceException e) {
		  System.out.print("Invalid Credentials");
		}
  }


}

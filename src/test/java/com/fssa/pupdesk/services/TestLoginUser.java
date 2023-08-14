package com.fssa.pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fssa.pupdesk.dao.UserDAO;
import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.services.exceptions.ServiceException;

public class TestLoginUser {
  @Test
  public void TestSuccesLogin() {
	  UserService userService = new UserService();
		UserDAO loginUser = new UserDAO();
	
		try {
			boolean user = userService.loginUser("bhirahatees@fssa.freshworks.com","Bhirahatees@123");
			System.out.println(user);
			assertTrue(user);
		} catch (ServiceException e) {
		  System.out.print("Invalid Credentials");
		  fail();
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

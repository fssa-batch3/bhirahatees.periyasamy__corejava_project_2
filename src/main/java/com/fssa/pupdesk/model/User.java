package com.fssa.pupdesk.model;

import java.security.SecureRandom;

public class User {

	private String firstname;
	private String lastname;
	private String email;
	private String teamCode;
	private String password;

	 static SecureRandom random = new SecureRandom();
	 
	 public User() {
		 
	 }

	public User(String firstname, String lastname, String email, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.teamCode = genarateTeamCode();
		this.password = password;
	}

	public User(String firstname, String lastname, String email, String teamCode, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.teamCode = teamCode;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public String getPassword() {
		return password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String genarateTeamCode() {
		 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        int length = 6;
	        StringBuilder keyBuilder = new StringBuilder();

	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            char randomChar = characters.charAt(index);
	            keyBuilder.append(randomChar);
	        }

	        return keyBuilder.toString();
	    }
	

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", TeamCode=" + teamCode
				+ ", password=" + password + "]";
	}
	
}

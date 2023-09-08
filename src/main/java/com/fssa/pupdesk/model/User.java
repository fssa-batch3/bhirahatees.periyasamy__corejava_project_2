package com.fssa.pupdesk.model;
import java.security.SecureRandom;


/**
 * Represents a user with various attributes.
 */
public class User {

	private String firstname;
	private String lastname;
	private String email;
	private String teamCode;
	private String password;
	private String profileImageUrl;

	// Create a static instance of SecureRandom for generating team codes
	static SecureRandom random = new SecureRandom();

	/**
	 * Default constructor for creating an empty User object.
	 */
	public User() {

	}

	/**
	 * Constructor for creating a User object with specified parameters.
	 *
	 * @param firstname The user's first name.
	 * @param lastname  The user's last name.
	 * @param email     The user's email address.
	 * @param password  The user's password.
	 */
	public User(String firstname, String lastname, String email, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.teamCode = generateTeamCode();
		this.password = password;
	}

	/**
	 * Constructor for creating a User object with all attributes specified.
	 *
	 * @param firstname The user's first name.
	 * @param lastname  The user's last name.
	 * @param email     The user's email address.
	 * @param teamCode  The user's team code.
	 * @param password  The user's password.
	 */
	public User(String firstname, String lastname, String email, String teamCode, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.teamCode = teamCode;
		this.password = password;
	}

	/**
	 * Getter method to retrieve the user's first name.
	 *
	 * @return The user's first name.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Getter method to retrieve the user's last name.
	 *
	 * @return The user's last name.
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Getter method to retrieve the user's email address.
	 *
	 * @return The user's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Getter method to retrieve the user's team code.
	 *
	 * @return The user's team code.
	 */
	public String getTeamCode() {
		return teamCode;
	}

	/**
	 * Getter method to retrieve the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method to update the user's first name.
	 *
	 * @param firstname The new first name of the user.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Setter method to update the user's last name.
	 *
	 * @param lastname The new last name of the user.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Setter method to update the user's email address.
	 *
	 * @param email The new email address of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Setter method to update the user's team code.
	 *
	 * @param teamCode The new team code of the user.
	 */
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * Setter method to update the user's password.
	 *
	 * @param password The new password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Generates a random team code consisting of uppercase letters and digits.
	 *
	 * @return The randomly generated team code.
	 */
	public static String generateTeamCode() {
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

	/**
	 * Getter method to retrieve the user's profile image URL.
	 *
	 * @return The user's profile image URL.
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * Setter method to update the user's profile image URL.
	 *
	 * @param profileImageUrl The new profile image URL of the user.
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return A string containing user details.
	 */
	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", teamCode=" + teamCode
				+ ", password=" + password + ", ProfileImageUrl=" + profileImageUrl + "]";
	}
}

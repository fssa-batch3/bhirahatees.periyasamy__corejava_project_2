package com.fssa.pupdesk.validation.exceptions;

/**
 * Custom exception class for representing validation errors related to user
 * data.
 */
public class InvalidUserException extends Exception {

	private static final long serialVersionUID = -1194860954774008955L;

	/**
	 * Constructs an InvalidUserException with the specified error message.
	 *
	 * @param msg The error message.
	 */
	public InvalidUserException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an InvalidUserException with the specified throwable cause.
	 *
	 * @param e The cause of the exception.
	 */
	public InvalidUserException(Throwable e) {
		super(e);
	}
}

package com.fssa.pupdesk.validation.exceptions;

/**
 * Custom exception class for representing validation errors related to ticket
 * data.
 */
public class InvalidTicketException extends Exception {

	private static final long serialVersionUID = -1194860954774008955L;

	/**
	 * Constructs an InvalidTicketException with the specified error message.
	 *
	 * @param msg The error message.
	 */
	public InvalidTicketException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an InvalidTicketException with the specified throwable cause.
	 *
	 * @param e The cause of the exception.
	 */
	public InvalidTicketException(Throwable e) {
		super(e);
	}
}

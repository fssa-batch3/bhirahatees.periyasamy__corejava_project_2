package com.fssa.pupdesk.validation.exceptions;

public class InvalidCommentException extends Exception {
	/**
	 * Constructs an InvalidCommentException with the specified error message.
	 *
	 * @param msg The error message.
	 */
	public InvalidCommentException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an InvalidCommentException with the specified throwable cause.
	 *
	 * @param e The cause of the exception.
	 */
	public InvalidCommentException(Throwable e) {
		super(e);
	}
}

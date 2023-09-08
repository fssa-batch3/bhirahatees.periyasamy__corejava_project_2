package com.fssa.pupdesk.services.exceptions;

/**
 * Custom exception class for service-related exceptions.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -8508529215117096666L;

	/**
	 * Constructs a new service exception with the specified detail message.
	 *
	 * @param msg The detail message describing the exception.
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a new service exception with the specified cause.
	 *
	 * @param e The cause of the exception.
	 */
	public ServiceException(Throwable e) {
		super(e);
	}

	/**
	 * Constructs a new service exception with the specified detail message and
	 * cause.
	 *
	 * @param msg The detail message describing the exception.
	 * @param e   The cause of the exception.
	 */
	public ServiceException(String msg, Throwable e) {
		super(msg, e);
	}
}

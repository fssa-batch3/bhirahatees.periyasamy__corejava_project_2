package com.fssa.pupdesk.services.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -8508529215117096666L;

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

}

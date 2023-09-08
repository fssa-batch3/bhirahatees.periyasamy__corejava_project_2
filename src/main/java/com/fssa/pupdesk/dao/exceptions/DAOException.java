package com.fssa.pupdesk.dao.exceptions;

public class DAOException extends Exception {

    private static final long serialVersionUID = -7798283981195321951L;

    /**
     * Constructs a new DAOException with the specified detail message.
     *
     * @param msg The detail message for the exception.
     */
    public DAOException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new DAOException with the specified cause.
     *
     * @param e The cause of the exception.
     */
    public DAOException(Throwable e) {
        super(e);
    }

    /**
     * Constructs a new DAOException with the specified detail message and cause.
     *
     * @param msg The detail message for the exception.
     * @param ex  The cause of the exception.
     */
    public DAOException(String msg, Throwable ex) {
        super(msg, ex);
    }
}

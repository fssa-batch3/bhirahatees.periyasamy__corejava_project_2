package pupdesk.validation.exceptions;

public class InvalidTicketException extends Exception {

	private static final long serialVersionUID = -1194860954774008955L;

	public InvalidTicketException(String msg) {
		super(msg);
	}

	public InvalidTicketException(Throwable e) {
		super(e);
	}
}
	
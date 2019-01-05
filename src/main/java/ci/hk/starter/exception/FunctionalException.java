package ci.hk.starter.exception;

import org.springframework.http.HttpStatus;

public class FunctionalException extends RuntimeException {

	/**
	 * For serialization purpose.
	 */
	private static final long serialVersionUID = -1686674134081470979L;
	
	private final HttpStatus status;
	
	/**
	 * No argument constructor.
	 */
	protected FunctionalException() {
		super();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	/**
	 * Constructor with Exception message.
	 * 
	 * @param message of the exception.
	 */
	public FunctionalException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}

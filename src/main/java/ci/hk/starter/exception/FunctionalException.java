package ci.hk.starter.exception;

import org.springframework.http.HttpStatus;

public class FunctionalException extends RuntimeException {

	/**
	 * For serialization purpose.
	 */
	private static final long serialVersionUID = -1686674134081470979L;
	
	private final HttpStatus status;
	
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

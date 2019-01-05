package ci.hk.starter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hamedkaramoko
 *
 * Class allowing to globally manage exception.
 */
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Method allowing to capture a set of exception and apply on it further actions.
	 * 
	 * @param functionalException represents the type of exception to capture.
	 * @param webRequest contains the information comming with the HTTP request.
	 * 
	 * @return an Exception.
	 */
	@ExceptionHandler(value= {FunctionalException.class})
	public ResponseEntity<Object> handleFunctionalException(FunctionalException functionalException, WebRequest webRequest){
		return handleExceptionInternal(functionalException, functionalException.getMessage(), new HttpHeaders(), functionalException.getStatus(), webRequest);
	}

}

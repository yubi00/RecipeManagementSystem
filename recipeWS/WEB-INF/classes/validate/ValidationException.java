/**
 * Exceptions that happen in the controller layer should be encapsulated in this
 * exception.
 *
 */

package validate;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

}

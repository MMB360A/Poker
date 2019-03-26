/**
 * 
 */
package version_graphics.model;

/**
 * @author mibe1
 * Exception thrown when the size of the Hand does not match with the evaluate Hand class or if one of the Cars in the Hand is null
 * TODO do some coole stuff here
 */
public class WrongHandException extends Exception {
		  public WrongHandException() { super(); }
		  public WrongHandException(String message) { super(message); }
		  public WrongHandException(String message, Throwable cause) { super(message, cause); }
		  public WrongHandException(Throwable cause) { super(cause); }
}


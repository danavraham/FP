package exeptions;

/**
 * GeneralException class create a new Exception called GeneralException that gets the
 * exception and prints it
 * 
 * @author dan
 */
public class GeneralException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * GeneralException() a method that gets the exception and prints it
	 * 
	 * @param message the string of Exception needs to be printed
	 */
	public GeneralException(String message) {
		super(message);
	}
}

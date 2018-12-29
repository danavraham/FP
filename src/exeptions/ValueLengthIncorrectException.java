package exeptions;

public class ValueLengthIncorrectException extends Exception{
	
	//Added by default
	private static final long serialVersionUID = 1L;
	
	static String staticMessage= ", you have entered a wrong number of charters";
	
	public ValueLengthIncorrectException (String message) {
		super( message +staticMessage);
	}
}

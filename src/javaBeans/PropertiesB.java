package javaBeans;

import java.util.ArrayList;
import exeptions.GeneralException;

/**
 * PropertiesB is a Class that creates the second basic property that is common
 * to the customer and company - Email, Passwors and an ArrayList of Coupons
 * 
 * @author Dan
 */
public abstract class PropertiesB extends PropertiesA {

	// -------------------Properties------------------

	private String email;
	private String password;
	private ArrayList<Coupon> coupons;

	// For REGULAR EXPRESSION Password check
	private static String passwordPattern = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,10}";

	// -------------------Constructor------------------

	/**
	 * Constructor for the PropertiesB class that gets an id, Email, password and an
	 * ArrayList of coupons
	 * 
	 * @param id       gets an ID
	 * @param email    gets an email
	 * @param password gets a password
	 * @param coupons  gets an ArrayList of coupons
	 * @throws GeneralException can throw GeneralException
	 */
	public PropertiesB(int id, String email, String password, ArrayList<Coupon> coupons) throws GeneralException {
		super(id);
		setPassword(password);
		setEmail(email);
		this.coupons = coupons;
	}

	/**
	 * Constructor for the PropertiesB class that gets an id, Email and password
	 * 
	 * @param id       gets an ID
	 * @param email    gets an email
	 * @param password gets a password
	 * @throws GeneralException can throw GeneralException
	 */
	public PropertiesB(int id, String email, String password) throws GeneralException {
		super(id);
		setPassword(password);
		setEmail(email);
	}

	/**
	 * Constructor for the PropertiesB class that gets an Email and password
	 * 
	 * @param email    gets an email
	 * @param password gets a password
	 * @throws GeneralException can throw GeneralException
	 */
	public PropertiesB(String email, String password) throws GeneralException {
		super();
		setPassword(password);
		setEmail(email);

	}

	// -------------------Setter+Getter------------------

	/**
	 * getEmail() method that gets the email
	 * 
	 * @return returns the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setEmail() Method that sets the email if it is valid must be right email
	 * format
	 * 
	 * @param email email to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setEmail(String email) throws GeneralException {
		if (!email.matches(".+\\@.+\\..+")) {
			throw new GeneralException("Please enter a valid Email");
		} else {
			this.email = email;
		}

	}

	/**
	 * getPassword() method that gets the password
	 * 
	 * @return returns the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setPassword() Method that sets the password if it is valid must contain 6-10
	 * charaters, at least one letter, at least one digit
	 * 
	 * @param password password to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setPassword(String password) throws GeneralException {

		if (!password.matches(passwordPattern)) {
			throw new GeneralException("password must contain 6-10 charaters, at least one letter, at least one digit");
		} else {
			this.password = password;
		}

	}

	/**
	 * getCoupons() method that gets the coupons ArrayList
	 * 
	 * @return returns the coupons ArrayList
	 */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * setCoupons() Method that sets the coupons ArrayList
	 * 
	 * @param coupons coupons ArrayList to set
	 * @throws GeneralException
	 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	// -------------------Methods-----------------

	/**
	 * toString() Override the toString method to return the Email, Passwors and the
	 * Coupons ArrayList
	 * 
	 * @return returns a string of all PropertiesB attributes
	 */
	@Override
	public String toString() {
		return super.toString() + " email=" + getEmail() + ", password=" + getPassword() + ", coupons=" + getCoupons();
	}

}

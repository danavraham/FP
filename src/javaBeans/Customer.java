package javaBeans;

import java.util.ArrayList;

import exeptions.GeneralException;

/**
 * Customer is a Class that creates a Customer and Extends the PropertiesB that
 * Extends PropertiesA
 * 
 * @author Dan
 */
public class Customer extends PropertiesB {

	// -------------------Properties------------------

	private String firstName;
	private String lastName;

	// -------------------Constructor------------------
	/**
	 * Constructor for the Customer class that gets id, email, password, firstName,
	 * lastName and coupons
	 *
	 * 
	 * @param id        gets a customer ID
	 * @param email     gets a customer email
	 * @param password  gets a customer password
	 * @param firstName gets a customer firstName
	 * @param lastName  gets a customer lastName
	 * @param coupons   gets an ArrayList of customer coupons
	 * @throws GeneralException can throw GeneralException
	 */
	public Customer(int id, String email, String password, String firstName, String lastName, ArrayList<Coupon> coupons)
			throws GeneralException {
		super(id, email, password, coupons);
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * Constructor for the Customer class that gets id, email, password, firstName,
	 * and lastName
	 *
	 * @param id        gets a customer ID
	 * @param email     gets a customer email
	 * @param password  gets a customer password
	 * @param firstName gets a customer firstName
	 * @param lastName  gets a customer lastName
	 * @throws GeneralException can throw GeneralException
	 */
	public Customer(int id, String email, String password, String firstName, String lastName) throws GeneralException {
		super(id, email, password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * Constructor for the Customer class that gets email, password, firstName, and
	 * lastName
	 *
	 * @param email     gets a customer email
	 * @param password  gets a customer password
	 * @param firstName gets a customer firstName
	 * @param lastName  gets a customer lastName
	 * @throws GeneralException can throw GeneralException
	 */
	public Customer(String email, String password, String firstName, String lastName) throws GeneralException {
		super(email, password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	// -------------------setters+getters------------------

	/**
	 * getFirstName() method that gets the customer firstName
	 * 
	 * @return returns the customer firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * setFirstName() Method that sets the customer firstName if it is valid must be
	 * between 2-15 letters, and letters only
	 * 
	 * @param firstName customer firstName to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setFirstName(String firstName) throws GeneralException {
		// check characters 2-15
		if (firstName.length() < 2 || firstName.length() > 15) {
			throw new GeneralException("Name number of charters must be 2-15");
		}
		// check only letters
		else if (!firstName.matches("^[a-zA-Z]+$")) {
			throw new GeneralException("Name contains only letters");
		} else {
			this.firstName = firstName;
		}
	}

	/**
	 * getLastName() method that gets the customer lastName
	 * 
	 * @return returns the customer lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * setLastName() Method that sets the customer lastName if it is valid must be
	 * between 2-15 letters, and letters only
	 * 
	 * @param lastName customer lastName to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setLastName(String lastName) throws GeneralException {
		// check characters 2-15
		if (lastName.length() < 2 || lastName.length() > 15) {
			throw new GeneralException("Name number of charters must be 2-15");
		}
		// check only letters
		else if (!lastName.matches("^[a-zA-Z]+$")) {
			throw new GeneralException("Name contains only letters");
		} else {
			this.lastName = lastName;
		}
	}

	// -------------------Methods-----------------

	/**
	 * toString() Override the toString method to return the customer attributes
	 * 
	 * @return returns a string all coupon attributes
	 */
	@Override
	public String toString() {
		return "Customer firstName= " + getFirstName() + ", lastName= " + getLastName() + " " + super.toString() + "\n";
	}

}

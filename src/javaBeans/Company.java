package javaBeans;

import java.util.ArrayList;

import exeptions.GeneralException;

/**
 * Company is a Class that creates a Company and Extends the PropertiesB that
 * Extends PropertiesA
 * 
 * @author Dan
 */
public class Company extends PropertiesB {

	// -------------------Properties------------------

	private String name;

	// -------------------Constructors------------------

	/**
	 * Constructor for the Company class that gets id, email, password, name and
	 * ArrayList of coupons
	 * 
	 * @param id       gets a company ID
	 * @param email    gets a company Email
	 * @param password gets a company password
	 * @param name     gets a company name
	 * @param coupons  gets an ArrayList of company coupons
	 * @throws GeneralException can throw GeneralException
	 */
	public Company(int id, String email, String password, String name, ArrayList<Coupon> coupons)
			throws GeneralException {
		super(id, email, password, coupons);
		setName(name);

	}

	/**
	 * Constructor for the Company class that gets id, email, password and name
	 * 
	 * @param id       gets a company ID
	 * @param email    gets a company Email
	 * @param password gets a company password
	 * @param name     gets a company name
	 * @throws GeneralException can throw GeneralException
	 */
	public Company(int id, String email, String password, String name) throws GeneralException {
		super(id, email, password);
		setName(name);

	}

	/**
	 * Constructor for the Company class that gets email, password and name
	 * 
	 * @param email    gets a company Email
	 * @param password gets a company password
	 * @param name     gets a company name
	 * @throws GeneralException can throw GeneralException
	 */
	public Company(String email, String password, String name) throws GeneralException {
		super(email, password);
		setName(name);

	}

	// -------------------setters+getters------------------

	/**
	 * getName() method that gets the company name
	 * 
	 * @return returns the company name
	 */
	public String getName() {

		return name;
	}

	/**
	 * setName() method that sets the company name if it is valid characters must be
	 * 2-15, only letters
	 * 
	 * @param name company name to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setName(String name) throws GeneralException {

		// check characters 2-15
		if (name.length() < 2 || name.length() > 15) {
			throw new GeneralException("Name number of charters must be 2-15");
		}
		// check only letters
		else if (!name.matches("^[a-zA-Z]+$")) {
			throw new GeneralException("Name contains only letters");
		} else {
			this.name = name;
		}

	}

	// -------------------methods-----------------

	/**
	 * toString() Override the toString method to return the company attributes
	 * 
	 * @return returns a string all company attributes
	 */
	@Override
	public String toString() {
		return "Company name= " + getName() + ", " + super.toString() + "\n";
	}

}

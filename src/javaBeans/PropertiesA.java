package javaBeans;

import exeptions.GeneralException;

/**
 * PropertiesA is a Class that creates the basic property that is common to the
 * coupon, customer and company- the ID
 * 
 * @author Dan
 */
public abstract class PropertiesA {

	// -------------------Properties------------------

	private int id;

	// -------------------Constructor------------------

	/**
	 * Constructor for the PropertiesA class that gets an id
	 * 
	 * @param id gets an ID
	 * @throws GeneralException can throw GeneralException
	 */
	public PropertiesA(int id) throws GeneralException {
		super();
		setId(id);

	}

	/**
	 * an empty constructor for the PropertiesA class
	 * 
	 * @throws GeneralException can throw GeneralException
	 */
	public PropertiesA() throws GeneralException {
		super();

	}

	// -------------------Setter+Getter------------------

	/**
	 * getId() method that gets the ID
	 * 
	 * @return returns the ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * setId() Method that sets the ID if it is valid must be more than 0
	 * 
	 * @param id Id to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setId(int id) throws GeneralException {
		if (id < 0) {
			throw new GeneralException("Id must be bigger than 0");

		} else {
			this.id = id;

		}
	}

	// -------------------Methods-----------------

	/**
	 * toString() Override the toString method to return the ID
	 * 
	 * @return returns a string of all ID attributes
	 */
	@Override
	public String toString() {
		return "id=" + getId();
	}

}

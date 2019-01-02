package javaBeans;

import exeptions.GeneralException;

public class Customer extends PropertiesB{
	
	//-------------------properties------------------
	
	private String firstName;
	private String lastName;
	
	//-------------------setter+getter------------------


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws GeneralException {
		//check characters 2-15
				if (firstName.length()<2 || firstName.length()>15) {
					throw new GeneralException("Name number of charters must be 2-15");
				}
				//check only letters
				else if (!firstName.matches("^[a-zA-Z]+$")) {
					throw new GeneralException("Name contains only letters");
				}
				else{
					this.firstName = firstName;
				}
	}
	


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws GeneralException {
		//check characters 2-15
				if (lastName.length()<2 || lastName.length()>15) {
					throw new GeneralException("Name number of charters must be 2-15");
				}
				//check only letters
				else if (!lastName.matches("^[a-zA-Z]+$")) {
					throw new GeneralException("Name contains only letters");
				}
				else{
					this.lastName = lastName;
				}
	}
	
	

    // -------------------constructors------------------
	public Customer(int id, String email, String password,String firstName, String lastName) throws GeneralException {
		super(id, email, password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	
	// -------------------methods-----------------
	
	@Override
	public String toString() {
		return "Customer [" +super.toString() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + "]";
	}
	

	

}

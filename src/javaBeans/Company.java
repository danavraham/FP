package javaBeans;

import java.util.ArrayList;

import exeptions.GeneralException;

public class Company extends PropertiesB{
	
	//-------------------properties------------------
	
	private String name;

	
	//-------------------setter+getter------------------


	public String getName() {
		
		return name;
	}

	public void setName(String name) throws GeneralException {
	
		//check characters 2-15
		if (name.length()<2 || name.length()>15) {
			throw new GeneralException("Name number of charters must be 2-15");
		}
		//check only letters
		else if (!name.matches("^[a-zA-Z]+$")) {
			throw new GeneralException("Name contains only letters");
		}
		else{
			this.name = name;
		}
		
	}

    // -------------------constructors------------------

	public Company(int id, String email, String password, String name, ArrayList<Coupon> coupons) throws GeneralException {
		super(id, email, password, coupons);
		setName(name);
		
	}
	
	public Company(String email, String password, String name) throws GeneralException {
		super(email, password);
		setName(name);
		
	}
	

	public Company(int id, String email, String password, String name) throws GeneralException {
		super(id, email, password);
		setName(name);
		
	}
	
	
	// -------------------methods-----------------

	

	@Override
	public String toString() {
		return "Company "+super.toString() + "name= " + getName() + "\n";
	}
	
	

}

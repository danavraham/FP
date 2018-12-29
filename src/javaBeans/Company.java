package javaBeans;

import java.util.ArrayList;

import exeptions.ValueLengthIncorrectException;

public class Company {
	
	//-------------------properties------------------
	private int id;
	private String name;
	private String email;
	private String Password;
	private ArrayList<Coupon> coupons;
	
	
	//-------------------setter+getter------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) throws ValueLengthIncorrectException {
		
		if (name.length()<2 || name.length()>15) {
			throw new ValueLengthIncorrectException("Number of charters must be 2-15");
		}
		else{
			this.name = name;
		}
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	
	
	

    // -------------------constructors------------------
	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) throws ValueLengthIncorrectException {
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	public Company(String name, String email, String password, ArrayList<Coupon> coupons) throws ValueLengthIncorrectException {
		this(0, name, email, password, coupons);
	}
	
	// -------------------methods-----------------

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", Password=" + Password + ", coupons="
				+ coupons + "]";
	}
	
	

}

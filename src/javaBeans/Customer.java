package javaBeans;

import java.util.ArrayList;

public class Customer {
	
	//-------------------properties------------------
	private int id;
	private String firstName;
	private String lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lastName;
	}

	public void setLastName(String LastName) {
		this.lastName = LastName;
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
	public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		this(0, firstName, lastName, email, password, coupons);
	}

	
	// -------------------methods-----------------
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", Password=" + Password + ", coupons=" + coupons + "]";
	}
	

	

}

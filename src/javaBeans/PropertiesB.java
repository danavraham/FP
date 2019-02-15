package javaBeans;

import java.util.ArrayList;
import exeptions.GeneralException;

public abstract class PropertiesB extends PropertiesA {
	


		//-------------------properties------------------
		
	private String email;
	private String password;
	private ArrayList<Coupon> coupons;
	
	//For REGULAR EXPRESSION Password check
	private static String passwordPattern="(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,10}";
	
		
		//-------------------setter+getter------------------
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws GeneralException {
		if (!email.matches(".+\\@.+\\..+")) {
			throw new GeneralException("Please enter a valid Email");
		}
		else{
			this.email = email;
		}
		
	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws GeneralException {
	

		if (!password.matches(passwordPattern)) {
			throw new GeneralException("password must contain 6-10 charaters, at least one letter, at least one digit");
		}
		else{
			this.password = password;
		}
		
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
		

		// -------------------constructor------------------
			
	public PropertiesB(int id, String email, String password,ArrayList<Coupon> coupons) throws GeneralException {
		super(id);
		setPassword(password);
		setEmail(email);
		this.coupons = new ArrayList<>();
	}
	
	public PropertiesB(int id, String email, String password) throws GeneralException {
		super(id);
		setPassword(password);
		setEmail(email);
	}

	public PropertiesB(String email, String password) throws GeneralException {
		super();
		setPassword(password);
		setEmail(email);
		
	}
	
	
	
	
	
		
		
	
	
		
		// -------------------methods-----------------
		
	

	@Override
	public String toString() {
		return super.toString() + " email=" + getEmail() + ", password=" + getPassword() + ", coupons=" + getCoupons() ;
	}
		

}

import java.sql.Date;
import java.util.ArrayList;

import exeptions.GeneralException;
import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.Customer;

public class App {

	public static void main(String[] args) {
		
		
		try {
		Company c1 = new Company(1, "fd@fdd.com", "fgs3245", "fds");	
		Customer custumer1 = new Customer(2, "d@fd.fd", "password6", "fds", "dsf");
		Coupon coupon1 = new Coupon(1, 2, Category.Baby, "title", "description by us", new Date(2019, 1, 1),new Date(2019, 1, 4), 34, 55.0, "image.url");
		
		System.out.println(c1);
		System.out.println(custumer1);
		System.out.println(coupon1);
		
		c1.getCoupons().add(coupon1);
		c1.getCoupons().add(coupon1);
		System.out.println(c1);
		}
		catch(GeneralException e){
			System.out.println(e.getMessage());
		}
		

	}

}

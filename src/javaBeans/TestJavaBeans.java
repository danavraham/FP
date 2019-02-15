package javaBeans;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJavaBeans {

	public static void main(String[] args) {
		try {
			
			
			Company com1 = new Company ("company@fd.com", "com1234", "JB");	
			System.out.println(com1);
				
			Customer cus1 = new Customer("customer@fds.com", "Cus1234", "Ca", "Caa");
			System.out.println(cus1);
			
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Date start_date=date.parse("2019-11-11");
			Date end_date=date.parse("2020-11-11");
			
			
			Coupon cup1 = new Coupon(0, 1, Category.Baby, "wipes for babies", "72 wipes no perfume", start_date, end_date, 3, 13.5, "imagepath");
			System.out.println(cup1);		
		

			
		} catch (Exception exeption) {
			System.out.println(exeption.getMessage());
		}


	}

}

package main;

import java.sql.Date;
import java.text.SimpleDateFormat;

import dbdao.CompaniesDBDAO;
import facades.ClientFacade;
import facades.ClientType;
import facades.LoginManager;
import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.Customer;

public class App {
	
	
	
public static void checkItAllBitch() {
		
		LoginManager insatance= LoginManager.getInstance();
		try {
			Company company=new Company("1company@gmail.com", "123abcd", "1Company");
			Customer customer=new Customer("bobalice@gmail.com", "123abcd", "Bob", "Alice");
			
			
			String d1 = "2019-01-02";
			String d2 = "2020-01-02";
			java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
			java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
			
			
			
			
			
			
			Coupon coupon=new Coupon(0, 1, Category.Birthday, "a title eveergergergergeg", "svr wrgwrfr  rfwfwrefwefwef  wer wer we wefw erwe	wtfwerf", 
					startDate, endDate, 5, 54.5, "imagepath/../");
			
			
			ClientFacade clientfacade= insatance.login("admin@admin.com", "admin", ClientType.Administrator);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }




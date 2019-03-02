package main;

import java.text.SimpleDateFormat;

import facades.*;
import javaBeans.*;
import jobs.CouponExpirationDailyJob;

/**
 * Test class is the class that contains a testAll method that checks all the
 * logic actions for the system users- the Administrator, the Companies and the
 * Customers
 * 
 * @author dan
 *
 */
public class Test {

	/**
	 * testAll() the static method to check all the logic actions off the program
	 */
	public static void testAll() {
		
		System.out.println("----------------------Starting the testAll() method-----------------------------");
		
		//create an instance of the LoginManager
		LoginManager loginManager = LoginManager.getInstance();

		try {

//----------Starting the Expired coupon delete daily job			
			
			CouponExpirationDailyJob dailyJob = new CouponExpirationDailyJob();
			Thread dailyJobThread = new Thread(dailyJob, "daily job");
			dailyJobThread.start();

//---------start test Admin
			
			System.out.println("*************************************************************\n"
							+ "--------------Start testing the Administrator check----------\n"
							+ "*************************************************************");
			
			// create an Administrator Facade by Login with Email and password
			AdminFacade admin = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

			//creating objects for Admin
			
			//Company objects (email, password, name)
			Company com1 = new Company("1comp3anxsy@gmxail.com", "1qwerx1234", "aCompanyBob");
			Company com2 = new Company("2companxsy@gmxail.com", "2qwerx1234", "bCompanyAlice");
			Company com3 = new Company("3companxsy@gmxail.com", "3qwerx1234", "cCompanyCharlie");
			//Company object for update (company ID, email, password, name)
			Company comUpdate = new Company(2, "updatecomp3anxsy@gmxail.com", "Update1234", "bCompanyAlice");
		
			//Customer objects (email, password, name)
			Customer cus1 = new Customer("1cutosrme@gmail.com", "1234qwer", "alice", "Alice");
			Customer cus2 = new Customer("2csjtosme@gmaril.com", "2234qwer", "bob", "Bob");
			Customer cus3 = new Customer("3cusorsme@gmail.com", "3234qwer", "cloie", "cloie");
			//Customer object for update (customer ID, email, password, name)
			Customer cusUpdate = new Customer(1, "3cussorsme@gmail.com", "3234qwer", "cloie", "cloie");
			
			
			
			System.out.println("-----------------Checking the Admin options of the companies");
						
			
			//adding companies
			admin.addCompany(com1);
			admin.addCompany(com2);
			admin.addCompany(com3);
			//updating company
			admin.updateCompany(comUpdate);
			//delete company
			admin.deleteCompany(1);
			//getting all the companies in the DB
			System.out.println("------All the companies from the DB info");
			System.out.println(admin.getAllCompanies());
			//getting one company from the DB
			System.out.println("------One company from the DB info");
			System.out.println(admin.getOneCompany(3));

			System.out.println("-----------------Checking the Admin options of the customers");

			//adding customers
			admin.addCustomer(cus1);
			admin.addCustomer(cus2);
			admin.addCustomer(cus3);
			//updating customer
			admin.updateCustomer(cusUpdate);
			//delete customer
			admin.deleteCustomer(3);
			//getting all the customers in the DB
			System.out.println("------All the customers from the DB info");
			System.out.println(admin.getAllCustomers());
			//getting one customer from the DB
			System.out.println("------One customer from the DB info");
			System.out.println(admin.getOneCustomer(2));

//----------end test Admin

//----------start test Company

			System.out.println("*************************************************************\n"
							+ "----------------Start testing the Company check--------------\n"
							+ "*************************************************************");
			
			// create an Company Facade by Login with Email and password
			CompanyFacade company = (CompanyFacade) loginManager.login("3companxsy@gmxail.com", "3qwerx1234",
					ClientType.Company);

			//creating Coupon objects for Company
			
			//creating a date format for the start and end dates of coupons
			String d1 = "2019-01-02";
			String d2 = "2020-01-02";
			java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
			java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
			//Coupon objects
			Coupon coupon1 = new Coupon(0, company.getCompanyId(), Category.Birthday, "1Black Barrel-O-Slime",
					"1very detailed about Black Barrel-O-Slime", startDate, endDate, 5, 54.5, "imagepath/../");
			Coupon coupon2 = new Coupon(0, company.getCompanyId(), Category.Photography, "3Manfrotto Compact Action",
					"3The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ",
					startDate, endDate, 5, 82.5, "imagepath/../");
			Coupon coupon3 = new Coupon(0, company.getCompanyId(), Category.Photography, "4Manfrotto Compact Action",
					"4The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ",
					startDate, endDate, 5, 68.9, "imagepath/../");
			Coupon coupon4 = new Coupon(0, company.getCompanyId(), Category.Photography, "5Manfrotto Compact Action",
					"5The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ",
					startDate, endDate, 5, 100, "imagepath/../");
			Coupon coupon5 = new Coupon(0, company.getCompanyId(), Category.Photography, "6Manfrotto Compact Action",
					"6The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ",
					startDate, endDate, 5, 20, "imagepath/../");
			//Coupon object for update, with the coupon ID (first number) to choose wich one to update
			Coupon couponUpdate = new Coupon(1, 3, Category.Baby, "2Fisher-Price 3-in-1 Sit-to-Stand",
					"2From tummy time, to sitting and spinning, to standing and exploring all around -- this 3-in-1, jungle-themed",
					startDate, endDate, 5, 1000, "imagepath/../");
			
			//adding coupons
			company.addCoupon(coupon1);
			company.addCoupon(coupon2);
			company.addCoupon(coupon3);
			company.addCoupon(coupon4);
			company.addCoupon(coupon5);
			//updating coupon
			company.updateCoupon(couponUpdate);
			//deleting coupon
			company.deleteCoupon(2);
			//getting company coupons options from the DB
			System.out.println("------All the company coupons");
			System.out.println(company.getCompanyCoupons());
			System.out.println("------All the company coupons from a specific Category");
			System.out.println(company.getCompanyCouponsByCategory(Category.Baby));
			System.out.println("------All the company coupons up to a max price");
			System.out.println(company.getCompanyCouponsByMaxPrice(80));
			//getting company details
			
			System.out.println("------Company details");
			System.out.println(company.getCompanyDetails());

//----------end test Company

//----------start test Customer

			System.out.println("*************************************************************\n"
							+ "----------------Start testing the Customer check--------------\n"
							+ "*************************************************************");
			
			// create an Customer Facade by Login with Email and password
			CustomerFacade customer = (CustomerFacade) loginManager.login("2csjtosme@gmaril.com", "2234qwer",
					ClientType.customer);

			//purchasing coupons
			customer.purchaseCoupon(customer.getCouponByID(1));
			customer.purchaseCoupon(customer.getCouponByID(3));
			customer.purchaseCoupon(customer.getCouponByID(4));
			customer.purchaseCoupon(customer.getCouponByID(5));
			//getting customer coupons options from the DB
			System.out.println("------All the customer coupons");
			System.out.println(customer.getCustomerCoupons());
			System.out.println("------All the customer coupons from a specific Category");
			System.out.println(customer.getCustomerCouponsByCategory(Category.Baby));
			System.out.println("------All the customer coupons up to a max price");
			System.out.println(customer.getCustomerCouponsByMaxPrice(1300));
			//getting customer details
			System.out.println("------Customer details");
			System.out.println(customer.getCustomerDetails());

			
//----------end test Customer

			System.out.println("*************************************************************\n"
					+ "----------------End of all testings!!!--------------\n"
					+ "*************************************************************");
		
			//dailyJobThread.stop();

		} catch (Exception e) {// TODO Auto-generated catch block
			System.out.println(e.getMessage());
//			System.out.println(e.getStackTrace());
		}

		
	}// end of testAll

}

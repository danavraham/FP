package main;

import java.text.SimpleDateFormat;

import dbdao.CouponsDBDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CustomerDBDAO;
import facades.AdminFacade;
import facades.ClientFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.LoginManager;
import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.Customer;

public class Test {

//	import dbdao.CompaniesDBDAO;	
//		
//			Company com1 = new Company("1comp3anxsy@gmxail.com", "1qwerx1234", "comxdpanybob");
//			Company com2 = new Company("2companxsy@gmxail.com", "2qwerx1234", "comxpanybob");
//			Company comUpdate = new Company(2,"1comp3anxsy@gmxail.com", "2qerx1234", "comxpanybob");
//			admin.addCompany(com1);
//			admin.addCompany(com2);
//			admin.addCompany(com3);
//			admin.updateCompany(comUpdate);
//			admin.deleteCompany(1);
//			System.out.println(admin.getAllCompanies());
//			System.out.println(admin.getOneCompany(2));
//			
//			Customer cus1 = new Customer("1cutosrme@gmail.com", "1234qwer", "alice", "Alice");
//			Customer cus2 = new Customer("2csjtosme@gmaril.com", "2234qwer", "bob", "Bob");
//			Customer cus3 = new Customer("3cusorsme@gmail.com", "3234qwer", "cloie", "cloie");
//			Customer cusUpdate = new Customer(1,"3cussorsme@gmail.com", "3234qwer", "cloie", "cloie");
//			admin.addCustomer(cus1);
//			admin.addCustomer(cus2);
//			admin.addCustomer(cus3);
//			admin.updateCustomer(cusUpdate);
//			admin.deleteCustomer(3);
//			System.out.println(admin.getAllCustomers());
//			System.out.println(admin.getOneCustomer(2));
//		} catch (Exception e) {// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
//	}//end of testAdmin
	
	
//	public static void testCompany() {
//		
//		LoginManager loginManager = LoginManager.getInstance();
//		
//		try {
//			CompanyFacade company = (CompanyFacade) loginManager.login("1comp3anxsy@gmxail.com", "2qerx1234", ClientType.Company);
//			
//			String d1 = "2019-01-02";
//			String d2 = "2020-01-02";
//			java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
//			java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
//
//			
//			Coupon coupon1 = new Coupon(0, company.getCompanyId(), Category.Birthday, "Black Barrel-O-Slime",
//					"very detailed about Black Barrel-O-Slime", startDate, endDate, 5, 54.5,
//					"imagepath/../");
//			Coupon coupon2 = new Coupon(7, 2, Category.Baby, "Fisher-Price 3-in-1 Sit-to-Stand",
//					"From tummy time, to sitting and spinning, to standing and exploring all around -- this 3-in-1, jungle-themed", startDate, endDate, 5, 54.5,
//					"imagepath/../");
//			Coupon coupon3 = new Coupon(0, company.getCompanyId(), Category.Photography, "Manfrotto Compact Action",
//					"The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
//					"imagepath/../");

			
//			company.addCoupon(coupon1);
//			company.addCoupon(coupon3);
//			company.updateCoupon(coupon2);
//			company.deleteCoupon(50);
//			System.out.println(company.getCompanyCoupons());
//			System.out.println(company.getCompanyCouponsByCategory(Category.Baby));
//			System.out.println(	company.getCompanyCouponsByMaxPrice(53.5));
//			System.out.println(company.getCompanyDetails());
//			
//		} catch (Exception e) {// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		
//	}//end of testCompany
//
//	
//	public static void testCustomer() {
//		
//		LoginManager loginManager = LoginManager.getInstance();
//		
//		try {
//			CustomerFacade customer = (CustomerFacade) loginManager.login("2csjtosme@gmaril.com", "2234qwer", ClientType.customer);
//
//
//			
//			String d1 = "2019-01-02";
//			String d2 = "2020-01-02";
//			java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
//			java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
//			
//			
//			Coupon coupon1 = new Coupon(0, 3, Category.Birthday, "Black Barrel-O-Slime",
//					"very detailed about Black Barrel-O-Slime", startDate, endDate, 5, 54.5,
//					"imagepath/../");
//			Coupon coupon2 = new Coupon(7, 3, Category.Baby, "Fisher-Price 3-in-1 Sit-to-Stand",
//					"From tummy time, to sitting and spinning, to standing and exploring all around -- this 3-in-1, jungle-themed", startDate, endDate, 5, 54.5,
//					"imagepath/../");
//			Coupon coupon3 = new Coupon(0, 3, Category.Photography, "Manfrotto Compact Action",
//					"The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
//					"imagepath/../");
//
//			
//			
//			customer.purchaseCoupon(coupon1);
//			customer.purchaseCoupon(coupon2);
//			customer.purchaseCoupon(coupon3);
//			System.out.println(customer.getCustomerCoupons());
//			System.out.println(customer.getCustomerCouponsByCategory(Category.Baby));
//			System.out.println(customer.getCustomerCouponsByMaxPrice(1300));
//			System.out.println(customer.getCustomerDetails());
//			
//		} catch (Exception e) {// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		
//	}//end of testCustomer
//	
//	
//	
	public static void testAll() {
		
LoginManager loginManager = LoginManager.getInstance();
		
		try {
			
//			test Admin
			AdminFacade admin = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
		
		
			Company com1 = new Company("1comp3anxsy@gmxail.com", "1qwerx1234", "comxdpanybob");
			Company com2 = new Company("2companxsy@gmxail.com", "2qwerx1234", "comxpanybob");
			Company com3 = new Company("3companxsy@gmxail.com", "3qwerx1234", "comxpanybobx");
			Company comUpdate = new Company(2,"4comp3anxsy@gmxail.com", "2qerx1234", "comxpanybob");
			admin.addCompany(com1);
			admin.addCompany(com2);
			admin.addCompany(com3);
			admin.updateCompany(comUpdate);
			admin.deleteCompany(1);
			System.out.println(admin.getAllCompanies());
			System.out.println(admin.getOneCompany(2));
			
			Customer cus1 = new Customer("1cutosrme@gmail.com", "1234qwer", "alice", "Alice");
			Customer cus2 = new Customer("2csjtosme@gmaril.com", "2234qwer", "bob", "Bob");
			Customer cus3 = new Customer("3cusorsme@gmail.com", "3234qwer", "cloie", "cloie");
			Customer cusUpdate = new Customer(1,"3cussorsme@gmail.com", "3234qwer", "cloie", "cloie");
			admin.addCustomer(cus1);
			admin.addCustomer(cus2);
			admin.addCustomer(cus3);
			admin.updateCustomer(cusUpdate);
			admin.deleteCustomer(3);
			System.out.println(admin.getAllCustomers());
			System.out.println(admin.getOneCustomer(2));
			
//			end test Admin
			
//			test Company
			
			
				CompanyFacade company = (CompanyFacade) loginManager.login("3companxsy@gmxail.com", "3qwerx1234", ClientType.Company);
				
				String d1 = "2019-01-02";
				String d2 = "2020-01-02";
				java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
				java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);

				
				Coupon coupon1 = new Coupon(0, company.getCompanyId(), Category.Birthday, "1Black Barrel-O-Slime",
						"1very detailed about Black Barrel-O-Slime", startDate, endDate, 5, 54.5,
						"imagepath/../");
				Coupon coupon2 = new Coupon(1, 3, Category.Baby, "2Fisher-Price 3-in-1 Sit-to-Stand",
						"2From tummy time, to sitting and spinning, to standing and exploring all around -- this 3-in-1, jungle-themed", startDate, endDate, 5, 54.5,
						"imagepath/../");
				Coupon coupon3 = new Coupon(0, company.getCompanyId(), Category.Photography, "3Manfrotto Compact Action",
						"3The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
						"imagepath/../");
				Coupon coupon4 = new Coupon(0, company.getCompanyId(), Category.Photography, "4Manfrotto Compact Action",
						"4The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
						"imagepath/../");
				Coupon coupon5 = new Coupon(0, company.getCompanyId(), Category.Photography, "5Manfrotto Compact Action",
						"5The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
						"imagepath/../");
				Coupon coupon6 = new Coupon(0, company.getCompanyId(), Category.Photography, "6Manfrotto Compact Action",
						"6The grip head on the tripod has a knob that allows you to select between a photo and video function. When using the ", startDate, endDate, 5, 54.5,
						"imagepath/../");
//
//	
				company.addCoupon(coupon1);
				company.addCoupon(coupon3);
				company.addCoupon(coupon4);
				company.addCoupon(coupon5);
				company.addCoupon(coupon6);
				company.updateCoupon(coupon2);
				company.deleteCoupon(2);
				System.out.println(company.getCompanyCoupons());
				System.out.println(company.getCompanyCouponsByCategory(Category.Baby));
				System.out.println(	company.getCompanyCouponsByMaxPrice(53.5));
				System.out.println(company.getCompanyDetails());
//				
			
//			end test Company
			
//			test Customer
			
				CustomerFacade customer = (CustomerFacade) loginManager.login("2csjtosme@gmaril.com", "2234qwer", ClientType.customer);

				customer.purchaseCoupon(coupon1);
				customer.purchaseCoupon(coupon2);
				customer.purchaseCoupon(coupon5);
				customer.purchaseCoupon(coupon6);
//				System.out.println(customer.getCustomerCoupons());
//				System.out.println(customer.getCustomerCouponsByCategory(Category.Baby));
//				System.out.println(customer.getCustomerCouponsByMaxPrice(1300));
//				System.out.println(customer.getCustomerDetails());
//			
			
			
//			end test Customer
			
		} catch (Exception e) {// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		

	}//end of testAll
	
	
}

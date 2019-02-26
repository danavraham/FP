package dbdao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import exeptions.GeneralException;
import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.Customer;

public class TestDBDAO {

	public static void main(String[] args) {
		
		
		try {
			CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
			Company com1 = new Company("company@fd.com", "com1234", "JB");
			Company com2 = new Company("company@2fd.com", "com2234", "MAT");
			System.out.println(com1);
			companiesDBDAO.addCompany(com1);
			companiesDBDAO.addCompany(com2);
			Company update = new Company(1,"compxany@fd.com", "com1234", "JohnBryce");
			companiesDBDAO.updateCompany(update);
			System.out.println(companiesDBDAO.getOneCompany(1));
			System.out.println(companiesDBDAO.getAllCompanies());
			
			companiesDBDAO.deleteCompany(2);
//			
//			
//			
//			
//			CustomerDBDAO customerDBDAO = new CustomerDBDAO();
//			Customer cus1 = new Customer("2cudstomer@fds.com", "2Cus1234", "Ca", "Caa");
//			Customer cus2 = new Customer("2cuztomer@fds.com", "Cus1234", "ba", "baa");
//			customerDBDAO.addCustomer(cus1);
//			customerDBDAO.addCustomer(cus2);
//			Customer update = new Customer(2,"updatedCustomer@fds.com", "Cus1234", "Ca", "Caa");
//			customerDBDAO.updateCustomer(update);
//			customerDBDAO.deleteCustomer(2);
//			System.out.println(customerDBDAO.getOneCustomer(1));
//			System.out.println(customerDBDAO.getAllCustomers());
			
//		
			
//			
//			CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
//			
//			
//			String d1 = "2019-01-02";
//			String d2 = "2020-01-02";
//			java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
//			java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
//
//			
//			Coupon cup1 = new Coupon(0, 1, Category.Baby, "wipes-anckjscbskjbckabs", "72 wipes-xnsnkjancjnsckjnscn", startDate, endDate, 3, 13.5, "imagepath");
//			Coupon cup2 = new Coupon(0, 1, Category.Baking, "wipes-anckjscbskjbckabs", "72 wipes-xnsnkjancjnsckjnscn", startDate, endDate, 3, 13.5, "imagepath");
//			System.out.println(cup1);
//			couponsDBDAO.addCoupon(cup1);
//			couponsDBDAO.addCoupon(cup2);
//			
//			
//			Coupon cup3 = new Coupon(1, 1, Category.Baking, "Updated-wipes-anckjscbskjbckabs", "72 wipes-xnsnkjancjnsckjnscn", startDate, endDate, 3, 13.5, "imagepath");
//			couponsDBDAO.updateCoupon(cup3);
//			couponsDBDAO.deleteCoupon(1);
//			System.out.println(couponsDBDAO.getAllCoupons());
//			System.out.println(couponsDBDAO.getOneCoupon(2));
	
//			couponsDBDAO.addCouponPurchase(2, 3);
//			couponsDBDAO.deleteCouponPurchase(1, 3);
			
			
			
			

		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}
		

	}

}

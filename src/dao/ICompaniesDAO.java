package dao;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;

/**
 * ICompaniesDAO - the interface that define all the methods the Companies dbdao
 * will use.
 * 
 * @author dan
 *
 */

public interface ICompaniesDAO {

	/**
	 * isCompanyExists() - a method that gets the company email and password and
	 * checks if the company exist in the DB
	 * 
	 * @param email    - getting the company email
	 * @param Password - getting the company password
	 * @return True if company exist in the DB, False- if the company dose not exist
	 * @throws Exception
	 */
	boolean isCompanyExists(String email, String password) throws Exception;

	/**
	 * addCompany()- a method that adds a company to the companies table in the DB
	 * 
	 * @param addCompany - a Company object to add
	 * @throws Exception
	 */
	void addCompany(Company addCompany) throws Exception;

	/**
	 * updateCompany() - a method that updates company info in the companies table
	 * in the DB
	 * 
	 * @param updateCompany - a Company object to be update
	 * @throws Exception
	 */
	void updateCompany(Company updateCompany) throws Exception;

	/**
	 * deleteCompany() - a method that deletes a company from the companies table in
	 * the DB by company ID
	 * 
	 * @param deleteCompanyByID - a company ID thats needs to be deleted
	 * @throws Exception
	 */
	void deleteCompany(int deleteCompanyByID) throws Exception;

	/**
	 * getAllCompanies() - a method that gets all the companies info
	 * 
	 * @return an ArrayList of all the companies and their data in the DB
	 * @throws Exception
	 */
	ArrayList<Company> getAllCompanies() throws Exception;

	/**
	 * getOneCompany() - a method that gets a single company info from DB by company
	 * ID
	 * 
	 * @param getCompanyByID - a company ID to get the info of
	 * @return a Company object from DB with all the company data
	 * @throws Exception
	 */
	Company getOneCompany(int getCompanyByID) throws Exception;

	/**
	 * isCouponDescriptionExist() - a method that checks in the DB if company 
	 * already have the same Title for an existing coupon
	 * @param companyId - the company ID to check
	 * @param couponTitle - the title to check if already exists for the company
	 * @return True if the coupon title already exists for the company, 
	 * and False if it doesn't
	 * @throws Exception
	 */
	boolean isCouponTitleExist(int companyId, String couponTitle) throws Exception;

	/**
	 * getAllCompanyCoupons() - a method that returns from the DB 
	 * all the coupons for the company
	 * @param companyId - company ID to get the coupons for
	 * @return an ArrayList of company coupons
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCoupons(int companyId) throws Exception;

	/**
	 * getAllCompanyCouponsByCategory() - a method that returns from the DB 
	 * all the coupons for the company that are in a specific Category
	 * @param companyId - company ID to get the coupons for
	 * @param categoryId - category ID to get the coupons for
	 * @return an ArrayList of company coupons per specific category
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws Exception;

	/**
	 * getAllCompanyCouponsByMaxPrice() - a method that returns from the DB 
	 * all the coupons for the company that are less than a specific price
	 * @param companyId - company ID to get the coupons for
	 * @param maxPrice - the maximum price of company coupons to return
	 * @return an ArrayList of company coupons up to a specific price
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws Exception;

	/**
	 * isCompanyEmailExists() - a method that checks if company email is already in the DB
	 * @param email - company email to check
	 * @return True if company email already in the DB, False if dosen't
	 * @throws Exception
	 */
	boolean isCompanyEmailExists(String email) throws Exception;;

	/**
	 * * isCompanyNameExists() - a method that checks if company name is already in the DB
	 * @param name - company name to check
	 * @return True if company name already in the DB, False if dosen't
	 * @throws Exception
	 */
	boolean isCompanyNameExists(String name) throws Exception;;
}
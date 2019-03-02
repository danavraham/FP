package dao;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;

/**
 * ICompaniesDAO the interface that define all the methods the CompaniesDBDAO
 * will use to make changes on the DB.
 * 
 * @author dan
 *
 */
public interface ICompaniesDAO {

	/**
	 * isCompanyExists() a method that gets the company email and password and
	 * checks if the company exist in the DB
	 * 
	 * @param email    getting the company email
	 * @param password getting the company password
	 * @return True if company exist in the DB, False- if the company dose not exist
	 * @throws Exception can throws Exception
	 */
	boolean isCompanyExists(String email, String password) throws Exception;

	/**
	 * * isCompanyNameExists() a method that checks if company name is already in
	 * the DB
	 * 
	 * @param name company name to check
	 * @return True if company name already in the DB, False if dosen't
	 * @throws Exception can throws Exception
	 */
	boolean isCompanyNameExists(String name) throws Exception;

	/**
	 * isCompanyEmailExists() a method that checks if company email is already in
	 * the DB
	 * 
	 * @param email company email to check
	 * @return True if company email already in the DB, False if dosen't
	 * @throws Exception can throws Exception
	 */
	boolean isCompanyEmailExists(String email) throws Exception;

	/**
	 * isCompanyExistsById() a method that gets the company ID and checks if the
	 * company ID exist in the DB
	 * 
	 * @param companyId getting the company ID
	 * @return True if company ID exist in the DB, False- if the company ID dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	boolean isCompanyExistsById(int companyId) throws Exception;

	/**
	 * addCompany() a method that adds a company to the companies table in the DB
	 * 
	 * @param addCompany a Company object to add to the DB
	 * @throws Exception can throws Exception
	 */
	void addCompany(Company addCompany) throws Exception;

	/**
	 * updateCompany() a method that updates company info in the companies table in
	 * the DB by its ID
	 * 
	 * @param updateCompany a Company object to be update
	 * @throws Exception can throws Exception
	 */
	void updateCompany(Company updateCompany) throws Exception;

	/**
	 * deleteCompany() a method that deletes a company from the companies table in
	 * the DB by company ID
	 * 
	 * @param deleteCompanyByID - a company ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	void deleteCompany(int deleteCompanyByID) throws Exception;

	/**
	 * getOneCompany() a method that gets a single company info from DB by company
	 * ID
	 * 
	 * @param getCompanyByID a company ID to get the info of
	 * @return a Company object from DB with all the company data
	 * @throws Exception can throws Exception
	 */
	Company getOneCompany(int getCompanyByID) throws Exception;

	/**
	 * getCompanyIdByEmailAndPassword() a method that gets a single company info
	 * from DB by the company Email and Password
	 * 
	 * @param email    a company email to get the info of
	 * @param password a company password to get the info of
	 * @return a Company object from DB with all the company data
	 * @throws Exception can throws Exception
	 */
	int getCompanyIdByEmailAndPassword(String email, String password) throws Exception;

	/**
	 * getAllCompanies() a method that gets all the companies info from the DB
	 * 
	 * @return an ArrayList of all the companies and their data in the DB
	 * @throws Exception can throws Exception
	 */
	ArrayList<Company> getAllCompanies() throws Exception;

	/**
	 * getAllCompanyCoupons() a method that returns from the DB all the coupons of
	 * the company
	 * 
	 * @param companyId company ID to get the coupons for
	 * @return an ArrayList of company coupons
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCoupons(int companyId) throws Exception;

	/**
	 * getAllCompanyCouponsByCategory() a method that returns from the DB all the
	 * coupons for the company that are in a specific Category
	 * 
	 * @param companyId  company ID to get the coupons for
	 * @param category category to get the coupons for
	 * @return an ArrayList of company coupons per specific category
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws Exception;

	/**
	 * getAllCompanyCouponsByMaxPrice() a method that returns from the DB all the
	 * coupons for the company that are less than a specific price
	 * 
	 * @param companyId company ID to get the coupons for
	 * @param maxPrice  the maximum price of company coupons to return
	 * @return an ArrayList of company coupons up to a specific price
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws Exception;

	/**
	 * isCouponTitleExist() a method that checks in the DB if company already have
	 * the same Title for an existing coupon
	 * 
	 * @param companyId   the company ID to check
	 * @param couponTitle the title to check if already exists for the company
	 * @return True if the coupon title already exists for the company, and False if
	 *         it doesn't
	 * @throws Exception can throws Exception
	 */
	boolean isCouponTitleExist(int companyId, String couponTitle) throws Exception;
}
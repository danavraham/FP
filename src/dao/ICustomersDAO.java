package dao;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Coupon;
import javaBeans.Customer;

/**
 * ICustomersDAO - the interface that define all the methods the CustomersDBDAO
 * will use to make changes on the DB.
 * 
 * @author dan
 *
 */
public interface ICustomersDAO {

	/**
	 * isCustomerExists() a method that gets the customer email and password and
	 * checks if the customer exist in the DB
	 * 
	 * @param email    getting the customer email
	 * @param password getting the customer password
	 * @return True- if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	boolean isCustomerExists(String email, String password) throws Exception;

	/**
	 * isCustomerExistsById() a method that gets the customer ID and checks if the
	 * customer ID exist in the DB
	 *
	 * @param customerId customerId to check
	 * @return True- if customer ID exist in the DB, False- if the customer ID dose
	 *         not exist
	 * @throws Exception can throws Exception
	 */
	boolean isCustomerExistsById(int customerId) throws Exception;

	/**
	 * isCustomerEmailExists() a method that gets the customer email and checks if
	 * the customer email exist in the DB
	 * 
	 * @param email    getting the customer email
	 * @param password getting the customer password
	 * @return True- if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	boolean isCustomerEmailExists(String email) throws Exception;

	/**
	 * addCustomer() a method that adds a customer to the customers table in the DB
	 * 
	 * @param addCustomer a customer object to add
	 * @throws Exception can throws Exception
	 */
	void addCustomer(Customer addCustomer) throws Exception;

	/**
	 * updateCustomer() a method that updates customer info in the customers table
	 * in the DB
	 * 
	 * @param updateCustomer - a customer object to update
	 * @throws Exception can throws Exception
	 */
	void updateCustomer(Customer updateCustomer) throws Exception;

	/**
	 * deleteCustomer() a method that deletes a customer from the customers table in
	 * the DB by customer ID
	 * 
	 * @param deleteCustomerByID a customer ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	void deleteCustomer(int deleteCustomerByID) throws Exception;

	/**
	 * getOneCustomer() a method that gets a single customer info from DB by
	 * customer ID
	 * 
	 * @param getCustomerByID a customer ID to get the info of
	 * @return a Customer object from DB with all the customer data
	 * @throws Exception can throws Exception
	 */
	Customer getOneCustomer(int getCustomerByID) throws Exception;

	/**
	 * getAllCustomers() a method that gets all the customers info from the DB
	 * 
	 * @return an ArrayList of all the customers and their data in the DB
	 * @throws Exception can throws Exception
	 */
	ArrayList<Customer> getAllCustomers() throws Exception;

	/**
	 * isCouponExistForCustomer() a method that checks if the customer already
	 * purchased a specific coupon already
	 * 
	 * @param customerId customer Id to check coupons
	 * @param couponId   coupon id to check if already been purchased by customer
	 * @return True- if already purchased this coupon, False- if not
	 * @throws Exception can throws Exception
	 */
	boolean isCouponExistForCustomer(int customerId, int couponId) throws Exception;

	/**
	 * getAllCustomerCoupons() a method that returns all the purchased coupons of a
	 * specific customer
	 * 
	 * @param customerId customer id to get the coupons of
	 * @return ArrayList of coupons purchased by customer
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws Exception;

	/**
	 * getAllCustomerCouponsByCategory() a method that returns all the specific
	 * customer purchased coupons from specific category
	 * 
	 * @param customerId customer id to check coupons of
	 * @param category the category of coupons to return
	 * @return ArrayList of coupons purchased by customer for specific category
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCustomerCouponsByCategory(int customerId, Category category) throws Exception;

	/**
	 * getAllCustomerCouponsByMaxPrice() a method that returns all the specific
	 * customer purchased coupons up to a max price
	 * 
	 * @param customerId customer id to check coupons of
	 * @param maxPrice   the maximum price of coupons that will be returned
	 * @return ArrayList of coupons purchased by customer up to a max price
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCustomerCouponsByMaxPrice(int customerId, int maxPrice) throws Exception;

	/**
	 * getCustomerIdByEmailAndPassword() a method that returns a customer ID by
	 * getting its email and password
	 * 
	 * @param email    customer email to check
	 * @param password customer password to check
	 * @return ID of the specific customer
	 * @throws Exception can throws Exception
	 */
	int getCustomerIdByEmailAndPassword(String email, String password) throws Exception;

}
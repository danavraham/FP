package dao;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Coupon;
import javaBeans.Customer;

/**
 * ICustomersDAO - the interface that define all the methods the Customers dbdao
 * will use.
 * 
 * @author dan
 *
 */
public interface ICustomersDAO {

	/**
	 * isCustomerExists() - a method that gets the customer email and password and
	 * checks if the customer exist in the DB
	 * 
	 * @param email    - getting the customer email
	 * @param Password - getting the customer password
	 * @return True if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception
	 */
	boolean isCustomerExists(String email, String password) throws Exception;

	/**
	 * isCustomerEmailExists() - a method that gets the customer email 
	 * 
	 * @param email    - getting the customer email
	 * @param Password - getting the customer password
	 * @return True if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception
	 */
	boolean isCustomerEmailExists(String email) throws Exception;
	/**
	 * addCustomer() - a method that adds a customer to the customers table in the
	 * DB
	 * 
	 * @param addCustomer - a customer object to add
	 * @throws Exception
	 */
	void addCustomer(Customer addCustomer) throws Exception;

	/**
	 * updateCustomer() - a method that updates customer info in the customers table
	 * in the DB
	 * 
	 * @param updateCustomer - a customer object to update
	 * @throws Exception
	 */
	void updateCustomer(Customer updateCustomer) throws Exception;

	/**
	 * deleteCustomer() - a method that deletes a customer from the customers table
	 * in the DB by customer ID
	 * 
	 * @param deleteCustomerByID - a customer ID thats needs to be deleted
	 * @throws Exception
	 */
	void deleteCustomer(int deleteCustomerByID) throws Exception;

	/**
	 * getAllCustomers() - a method that gets all the customers info
	 * 
	 * @return an ArrayList of all the customers and their data in the DB
	 * @throws Exception
	 */
	ArrayList<Customer> getAllCustomers() throws Exception;

	/**
	 * getOneCustomer() - a method that gets a single customer info from DB by
	 * customer ID
	 * 
	 * @param getCustomerByID - a customer ID to get the info of
	 * @return a Customer object from DB with all the customer data
	 * @throws Exception
	 */
	Customer getOneCustomer(int getCustomerByID) throws Exception;

	/**
	 * isCouponExistForCustomer() - a method that checks if the customer already
	 * purchased the coupon already
	 * @param customerId - customer Id to check coupons
	 * @param couponId - coupon id to check if already been purchased by customer
	 * @return True if already purchased this coupon, False id not
	 * @throws Exception
	 */
	boolean isCouponExistForCustomer(int customerId, int couponId) throws Exception;

	/**
	 *getAllCustomerCoupons() - a method that returns all the specific customer 
	 *purchased coupons
	 * @param customerId - customer id to check coupons of
	 * @return ArrayList of coupons purchased by customer
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws Exception;

	/**
	 *getAllCustomerCouponsByCategory() - a method that returns all the specific customer 
	 *purchased coupons from specific category
	 * @param customerId - customer id to check coupons of
	 * @param categoryId - the category of coupons to return
	 * @return ArrayList of coupons purchased by customer for specific category
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCustomerCouponsByCategory(int customerId, Category category) throws Exception;

	ArrayList<Coupon> getAllCustomerCouponsByMaxPrice(int customerId, int maxPrice) throws Exception;

}
package facades;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Coupon;
import javaBeans.Customer;

/**
 * CustomerFacade This is a facade class of the customers. it contains all the
 * actions the customers in the system can do. this class extends ClientFacade
 * and Override it's login method
 * 
 * @author dan
 *
 */
public class CustomerFacade extends ClientFacade {

	// ----------------------Properties--------------------

	private int customerId;

	// -----------------------------G&S-----------------------------------

	/**
	 * getCompanyId() a method that gets the customer ID
	 * 
	 * @return
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * setCustomerId() method that sets the customer ID
	 * 
	 * @param customerId customer ID to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	// -----------------------------Constructor-----------------------------------

	/**
	 * CustomerFacade constructor gets the email and the password of the Customer
	 * and checks if login is possible with this email and password
	 * 
	 * @param email    gets email to check for login details
	 * @param password gets password to check for login details
	 * @throws Exception can throws Exception
	 */
	public CustomerFacade(String email, String password) throws Exception {

		if (!login(email, password)) {
			throw new Exception("Customer log in failed- please try again");
		} else {
			// setting the customer ID for the use of the methods
			setCustomerId(customersDBDAO.getCustomerIdByEmailAndPassword(email, password));
		}

	}

	// ----------------------------Methods-----------------------------------

	/**
	 * login() method that Override the login method of the LoginManager and
	 * compares the login details of the customer to the details in the DB to see if
	 * the details are correct
	 * 
	 * @param email    gets an email to check
	 * @param password gets a password to check
	 * @return return true if login successful, and false if not
	 * @throws Exception can throws Exception
	 */
	@Override
	public boolean login(String email, String password) throws Exception {

		if (customersDBDAO.isCustomerExists(email, password)) {
			System.out.println("Customer Login Successful");
			return true;
		} else {
			return false;
		}

	}

	/**
	 * purchaseCoupon() is a method that used to purchase a coupon for customer.
	 * customer cannot purchase a coupon more than once. coupon can be purchased
	 * only if its amount is more than 0. coupon can be purchased only if not
	 * expired. once purchased, the amount of coupons reduced by 1
	 * 
	 * @param coupon - a Coupon object to be purchased by current customer
	 * @throws Exception
	 */
	public void purchaseCoupon(Coupon coupon) throws Exception {
		// check if customer did not purchased this coupon in the past
		if (!customersDBDAO.isCouponExistForCustomer(customerId, coupon.getId())) {
			// check if this coupon has any inventory left
			if (couponsDBDAO.getOneCoupon(coupon.getId()).getAmount() > 0) {
				// check if this coupon is not expired
				if (!couponsDBDAO.isCouponExpiered(coupon.getId())) {

					// Purchase 1 coupon for customer
					couponsDBDAO.addCouponPurchase(coupon.getId(), this.getCustomerId());
					// reduce this coupon amount by 1
					couponsDBDAO.updateCouponAmount(coupon.getId(), -1);

				} else {
					throw new Exception("Cannot purchase coupon, coupon is expired");
				}

			} else {
				throw new Exception("Cannot purchase coupon, no more coupons left");
			}

		} else {
			throw new Exception("Cannot purchase coupon, you already purchased this one in the past");
		}
	}

	/**
	 * getCouponByID() is a method gets a Coupon Object from DB by its ID
	 * 
	 * @param couponId the ID of the coupon to return
	 * @return an Coupon Object
	 * @throws Exception can throws Exception
	 */
	public Coupon getCouponByID(int couponId) throws Exception {

		return couponsDBDAO.getOneCoupon(couponId);

	}

	/**
	 * getCustomerCoupons() is a method gets all coupons and their info of current
	 * logged in customer
	 * 
	 * @return an ArrayList of customer coupons
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCustomerCoupons() throws Exception {

		return customersDBDAO.getAllCustomerCoupons(this.getCustomerId());

	}

	/**
	 * getCustomerCouponsByCategory() is a method gets all coupons of current
	 * customer from a specific category
	 * 
	 * @param category the Category of coupons of customer to return
	 * @return an ArrayList of current customer coupons from a specific category
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) throws Exception {
		return customersDBDAO.getAllCustomerCouponsByCategory(customerId, category);
	}

	/**
	 * getCustomerCouponsByMaxPrice() is a method gets all coupons of current
	 * customer up to a max price
	 * 
	 * @param maxPrice- the max price of current customer coupons to return
	 * @return an ArrayList of current customer coupons up to max price
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCustomerCouponsByMaxPrice(int maxPrice) throws Exception {

		return customersDBDAO.getAllCustomerCouponsByMaxPrice(customerId, maxPrice);

	}

	/**
	 * getCustomerDetails() is a method that returns the logged in customer details
	 * 
	 * @return an Customer object of the logged in customer
	 * @throws Exception can throws Exception
	 */
	public Customer getCustomerDetails() throws Exception {
		return customersDBDAO.getOneCustomer(customerId);
	}

}

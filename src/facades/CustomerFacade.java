package facades;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Coupon;
import javaBeans.Customer;

public class CustomerFacade extends ClientFacade {

	// ----------------------Properties--------------------

	private int customerId;

	// -----------------------------CTOR-----------------------------------
	public CustomerFacade() {
		setCustomerId(customerId);
	}

	// -----------------------------G&S-----------------------------------

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	// ----------------------------Methods-----------------------------------

	@Override
	public boolean login(String email, String password) {
		
		// TODO Auto-generated method stub
		return super.login(email, password);
	}

	/**
	 * purchaseCoupon() is a method that used to purchase a coupon 
	 * customer cannot purchase a coupon more than once
	 * coupon can be purchased only if its amount is more than 0
	 * coupon can be purchased only if not expired 
	 * once purchased, the amount of coupons reduced by 1
	 * @param coupon - a Coupon object to be purchased by current customer
	 * @throws Exception
	 */
	public void purchaseCoupon(Coupon coupon) throws Exception {
		
			//check if customer purchased this coupon in the past
		if (!customersDBDAO.isCouponExistForCustomer(customerId, coupon.getId())
				//check if this coupon has any left
				&& couponsDBDAO.isCouponLeft(coupon.getId())
				//check if this coupon has been  expired
				&& !couponsDBDAO.isCouponExpiered(coupon.getId())) {
			
					couponsDBDAO.addCouponPurchase(customerId, coupon.getId());
					//reduce this coupon amount by 1
					couponsDBDAO.updateCouponAmount(coupon.getId(), -1);
					
	     	}

	}

	/**
	 * getCustomerCoupons() is a method gets all coupons of current customer
	 * @return an ArrayList of customer coupons
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCustomerCoupons() throws Exception {
		
		return customersDBDAO.getAllCustomerCoupons(customerId);
		
	}

	
	/**
	 * getCustomerCouponsByCategory() is a method gets all coupons of current customer
     * from a specific category
	 * @param category- the Category of coupons of customer to return
	 * @return an ArrayList of current customer coupons from a specific category
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) throws Exception {
		return customersDBDAO.getAllCustomerCouponsByCategory(customerId, category);
	}

	
	/**
	 * getCustomerCouponsByMaxPrice() is a method gets all coupons of current customer
	 *  up to a max price
	 * @param maxPrice- the max price of current customer coupons to return
	 * @return an ArrayList of current customer coupons up to max price
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCustomerCouponsByMaxPrice(int maxPrice) throws Exception {

		return customersDBDAO.getAllCustomerCouponsByMaxPrice(customerId, maxPrice);
		
	}

	
	/**
	 * getCustomerDetails() is a method that returns the logged in customer details
	 * @return an Customer object of the logged in customer
	 * @throws Exception
	 */
	public Customer getCustomerDetails() throws Exception {
		return customersDBDAO.getOneCustomer(customerId);
	}

}

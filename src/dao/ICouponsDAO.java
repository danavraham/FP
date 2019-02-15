package dao;

import java.util.ArrayList;
import javaBeans.Coupon;

/**
 * ICouponsDAO - the interface that define all the methods the Coupons dbdao
 * will use.
 * 
 * @author dan
 *
 */
public interface ICouponsDAO {

	/**
	 * addCoupon() - a method that adds a coupon to the coupons table in the DB
	 * 
	 * @param addCoupon - a Coupon object to add
	 * @throws Exception
	 */
	void addCoupon(Coupon addCoupon) throws Exception;

	/**
	 * updateCoupon() - a method that Updates a coupon in the coupons table in the
	 * DB
	 * 
	 * @param updateCoupon - a Coupon object to be update
	 * @throws Exception
	 */
	void updateCoupon(Coupon updateCoupon) throws Exception;

	/**
	 * deleteCoupon() - a method that deletes a coupon from the coupons table in the
	 * DB by coupon ID
	 * 
	 * @param deleteCouponByID - a coupon ID thats needs to be deleted
	 * @throws Exception
	 */
	void deleteCoupon(int deleteCouponByID) throws Exception;

	/**
	 * getAllCoupons() - a method that gets all the coupons info
	 * 
	 * @return an ArrayList of all the coupons and their data in the DB
	 * @throws Exception
	 */
	ArrayList<Coupon> getAllCoupons() throws Exception;

	/**
	 * getOneCoupon() - a method that gets a single coupon info from DB by coupon ID
	 * 
	 * @param getCouponByID - a coupon ID to get the info of
	 * @return a Coupon object from DB with all the coupon data
	 * @throws Exception
	 */
	Coupon getOneCoupon(int getCouponByID) throws Exception;

	/**
	 * addCouponPurchase() - a method that adds a purchase of a coupon by a customer
	 * 
	 * @param customerID - the customer that purchased ID
	 * @param couponId   - the coupon purchased ID
	 * @throws Exception
	 */
	void addCouponPurchase(int customerID, int couponId) throws Exception;

	/**
	 * deleteCouponPurchase() - a method that deletes a purchase made
	 * 
	 * @param customerID - the customer that purchased ID
	 * @param couponId   - the coupon purchased ID
	 */
	void deleteCouponPurchase(int customerID, int couponId) throws Exception;

	/**
	 * isCouponLeft() - a method that check if the coupon has more than 0 amount
	 * @param couponId - coupon ID to check if any left
	 * @return True if there is 1 or more coupons left
	 * @throws Exception
	 */
	boolean isCouponLeft(int couponId) throws Exception;

	/**
	 * isCouponExpiered() - a method that check if the coupon has expired
	 * @param couponId - coupon ID to check if expired
	 * @return True if coupon has expired, Fale if not
	 * @throws Exception
	 */
	boolean isCouponExpiered(int couponId) throws Exception;
	
	
	void deleteAllCompanyCoupons (int companyId) throws Exception;
	
	void deleteAllCouponPurchaseByCompanyId(int companyId) throws Exception;

	void deleteAllCouponPurchaseByCustomerId(int customerId) throws Exception;

	void deleteAllCouponPurchaseByCouponId(int couponId) throws Exception;
	
	void updateCouponAmount(int couponId, int couponManipulation)throws Exception;

}
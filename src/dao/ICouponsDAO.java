package dao;

import java.util.ArrayList;

import javaBeans.Coupon;

/**
 * ICouponsDAO the interface that define all the methods the CouponsDBDAO will
 * use to make changes on the DB.
 * 
 * @author dan
 *
 */
public interface ICouponsDAO {

	/**
	 * isCouponExistById() a method that check if the coupon exist by its ID
	 * 
	 * @param couponId coupon ID to check if exist in DB
	 * @return True if coupon ID exist in the DB, false if not
	 * @throws Exception can throws Exception
	 */
	boolean isCouponExistById(int couponId) throws Exception;

	/**
	 * isCouponLeft() a method that check if the coupon has more than 0 amount by
	 * coupon ID
	 * 
	 * @param couponId coupon ID to check if any left
	 * @return True if there is 1 or more coupons left, false if not
	 * @throws Exception can throws Exception
	 */
	boolean isCouponLeft(int couponId) throws Exception;

	/**
	 * addCoupon() a method that adds a coupon to the coupons table in the DB
	 * 
	 * @param addCoupon a Coupon object to add to DB
	 * @throws Exception can throws Exception
	 */
	void addCoupon(Coupon addCoupon) throws Exception;

	/**
	 * isCouponExpiered() a method that check if the coupon has expired by its ID
	 * 
	 * @param couponId coupon ID to check if expired
	 * @return True if coupon has expired, False if not
	 * @throws Exception can throws Exception
	 */
	boolean isCouponExpiered(int couponId) throws Exception;

	/**
	 * updateCoupon() a method that Updates a coupon in the coupons table in the DB
	 * 
	 * @param updateCoupon a Coupon object to be update
	 * @throws Exception can throws Exception
	 */
	void updateCoupon(Coupon updateCoupon) throws Exception;

	/**
	 * deleteCoupon() a method that deletes a coupon from the coupons table in the
	 * DB by coupon ID
	 * 
	 * @param deleteCouponByID a coupon ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	void deleteCoupon(int deleteCouponByID) throws Exception;

	/**
	 * deleteAllCompanyCoupons() a method that deletes all company coupons from the
	 * coupons table in the DB by company ID
	 * 
	 * @param companyId a company ID thats needs to be delete its coupons
	 * @throws Exception can throws Exception
	 */
	void deleteAllCompanyCoupons(int companyId) throws Exception;

	/**
	 * getOneCoupon() a method that gets a single coupon info from DB by coupon ID
	 * 
	 * @param getCouponByID a coupon ID to get the info of
	 * @return a Coupon object from DB with all the coupon data
	 * @throws Exception can throws Exception
	 */
	Coupon getOneCoupon(int getCouponByID) throws Exception;

	/**
	 * getAllCoupons() a method that gets all the coupons and their info from the DB
	 * 
	 * @return an ArrayList of all the coupons and their data in the DB
	 * @throws Exception can throws Exception
	 */
	ArrayList<Coupon> getAllCoupons() throws Exception;

	/**
	 * updateCouponAmount() a method that updates a specific coupon amount. -1 if
	 * purchased and 1 if purchase is canceled.
	 * 
	 * @param couponId           the coupon ID that needed to update its amount
	 * @param couponManipulation the manipulation on the coupon amount that is
	 *                           needed -1 if purchased and 1 if purchase is
	 *                           canceled.
	 * @throws Exception can throws Exception
	 */
	void updateCouponAmount(int couponId, int couponManipulation) throws Exception;

	/**
	 * addCouponPurchase() a method that adds a purchase of a coupon by a customer
	 * in the coustomers_vs_coupons table in the DB
	 * 
	 * @param customerID the customer that purchased ID
	 * @param couponId   the coupon purchased ID
	 * @throws Exception can throws Exception
	 */
	void addCouponPurchase(int customerID, int couponId) throws Exception;

	/**
	 * deleteCouponPurchase() a method that deletes a specific coupon purchase made
	 * by a specific customer from the coustomers_vs_coupons table in the DB
	 * 
	 * @param customerID the customer that purchased ID
	 * @param couponId   the coupon purchased ID
	 * @throws Exception can throws Exception
	 */
	void deleteCouponPurchase(int customerID, int couponId) throws Exception;

	/**
	 * deleteAllCouponPurchaseByCouponId() a method that deletes all the coupon
	 * purchases made of a specific coupon from the coustomers_vs_coupons table in
	 * the DB
	 * 
	 * @param couponId the coupon ID needs to delete
	 * @throws Exception can throws Exception
	 */
	void deleteAllCouponPurchaseByCouponId(int couponId) throws Exception;

	/**
	 * deleteAllCouponPurchaseByCustomerId() a method that deletes all the coupon
	 * purchases made by a specific customer from the coustomers_vs_coupons table in
	 * the DB
	 * 
	 * @param customerId the customer ID needs to delete
	 * @throws Exception can throws Exception
	 */
	void deleteAllCouponPurchaseByCustomerId(int customerId) throws Exception;

	/**
	 * deleteAllCouponPurchaseByCompanyId() a method that deletes all the coupon
	 * purchases of coupons related to a specific company from the
	 * coustomers_vs_coupons table in the DB
	 * 
	 * @param companyId the company ID needs to delete
	 * @throws Exception can throws Exception
	 */
	void deleteAllCouponPurchaseByCompanyId(int companyId) throws Exception;

	/**
	 * deleteAllExpiredCoupons() a method for the use of the daily job that deletes
	 * all the coupon that expired and from the purchases history
	 * 
	 * @throws Exception can throws Exception
	 */
	void deleteAllExpiredCoupons() throws Exception;

}
package facades;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;

/**
 * @author dan
 *
 */
public class CompanyFacade extends ClientFacade {

	// ----------------------Properties--------------------

	private int companyId;

	// -----------------------------CTOR-----------------------------------
	public CompanyFacade() {
		setCompanyId(companyId);
	}

	// -----------------------------G&S-----------------------------------

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	// ----------------------------Methods-----------------------------------

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return super.login(email, password);
	}

	/**
	 * addCoupon() - a method that adds a coupon to the DB after it checks if new
	 * coupon title already exist for company
	 * 
	 * @param coupon - new Coupon Object
	 * @throws Exception if company already has this coupon title
	 */
	public void addCoupon(Coupon coupon) throws Exception {
		if (companiesDBDAO.isCouponTitleExist(coupon.getCompanyId(), coupon.getTitle())) {
			couponsDBDAO.addCoupon(coupon);

		} else {
			throw new Exception("Coupon title alreade exist for company");
		}
	}

	/**
	 * updateCoupon() - a method that updates a coupon in the DB after it checks
	 * company ID isn't changed.
	 * 
	 * @param coupon - new Coupon Object to update
	 * @throws Exception if trying to update company ID
	 */
	public void updateCoupon(Coupon coupon) throws Exception {

		if (coupon.getCompanyId() != companiesDBDAO.getOneCompany(coupon.getId()).getId()) {

			couponsDBDAO.updateCoupon(coupon);
		} else {
			throw new Exception("Cannot update company ID or Coupon ID");
		}

	}

	/**
	 * deleteCoupon() - a method that deletes a coupon from the DB the method
	 * deletes this coupon from coupon purchase history
	 * 
	 * @param couponIdToDelete - the ID of coupon needs to be deleted
	 * @throws Exception
	 */
	public void deleteCoupon(int couponIdToDelete) throws Exception {

		couponsDBDAO.deleteAllCouponPurchaseByCouponId(couponIdToDelete);
		couponsDBDAO.deleteCoupon(couponIdToDelete);

	}

	/**
	 * getCompanyCoupons() - a method that gets all the company coupons from the DB
	 * 
	 * @return an Array List of company coupons
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws Exception {

		return companiesDBDAO.getAllCompanyCoupons(companyId);
	}

	/**
	 * getCompanyCouponsByCategory() - a method that gets all the company coupons
	 * that are from a specific category
	 * 
	 * @param category - the specific category to return coupons for
	 * @return an Array List of company category coupons
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) throws Exception {

		return companiesDBDAO.getAllCompanyCouponsByCategory(companyId, category);
	}

	
	/**
	 * getCompanyCouponsByMaxPrice() - a method that gets all the company coupons
	 * that are up to a maximum price
	 * 
	 * @param maxPrice - the maximum price of the coupons to return
	 * @return an Array List of company coupons up to max price
	 * @throws Exception
	 */
	public ArrayList<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws Exception {

		return companiesDBDAO.getAllCompanyCouponsByMaxPrice(companyId, maxPrice);
	}

	
	/**
	 * getCompanyDetails() - a method that gets back the company details
	 * 
	 * @return an Company Object of the registered company
	 * @throws Exception
	 */
	public Company getCompanyDetails() throws Exception {
		return companiesDBDAO.getOneCompany(companyId);

	}

}

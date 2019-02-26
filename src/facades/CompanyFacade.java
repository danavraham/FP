package facades;

import java.util.ArrayList;

import exeptions.GeneralException;
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
	public CompanyFacade(String email, String password) throws Exception {
		if (!login(email, password)) {
			throw new GeneralException("Company log in failed- please try again");
		} else {
			this.companyId = companiesDBDAO.getCompanyIdByEmailAndPassword(email, password);
		}

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
	public boolean login(String email, String password) throws Exception {
		if (companiesDBDAO.isCompanyExists(email, password)) {
			System.out.println("Company log in successful");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * addCoupon() - a method that adds a coupon to the DB after it checks if new
	 * coupon title already exist for company
	 * 
	 * @param coupon - new Coupon Object
	 * @throws Exception if company already has this coupon title
	 */
	public void addCoupon(Coupon coupon) throws Exception {
		if (!companiesDBDAO.isCouponTitleExist(coupon.getCompanyId(), coupon.getTitle())) {
			couponsDBDAO.addCoupon(coupon);

		} else {
			throw new Exception("Cannot add coupon - coupon title alreade exist for company");
		}
	}

	/**
	 * updateCoupon() - a method that updates a coupon in the DB after it checks if
	 * coupon ID exist in DB and that company ID isn't changed.
	 * 
	 * @param coupon - new Coupon Object to update
	 * @throws Exception if trying to update company ID
	 */
	public void updateCoupon(Coupon coupon) throws Exception {

		if (couponsDBDAO.isCouponExistById(coupon.getId())) {
			if (companiesDBDAO.isCompanyExistsById(coupon.getCompanyId())) {
//				if (coupon.getCompanyId() == companiesDBDAO.getOneCompany(coupon.getCompanyId()).getId()) {
				if(coupon.getCompanyId()==couponsDBDAO.getOneCoupon(coupon.getId()).getCompanyId()) {
					couponsDBDAO.updateCoupon(coupon);
				} else {
					throw new Exception("Cannot update coupon  - cannot change company ID");
				}
			} else {
				throw new Exception("Cannot update coupon  - company ID dont exist in DB");
			}
		} else {
			throw new Exception("Cannot update coupon - coupon ID dont exist in DB");
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
		if(couponsDBDAO.isCouponExistById(couponIdToDelete)) {
		couponsDBDAO.deleteAllCouponPurchaseByCouponId(couponIdToDelete);
		couponsDBDAO.deleteCoupon(couponIdToDelete);
		}else {
			throw new Exception("Cannot delete coupon - coupon ID dont exist in DB");
		}
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

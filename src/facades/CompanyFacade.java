package facades;

import java.util.ArrayList;

import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;

/**
 * CompanyFacade This is a facade class of the companies. it contains all the
 * actions the companies in the system can do. this class extends ClientFacade
 * and Override it's login method
 * 
 * @author dan
 *
 */
public class CompanyFacade extends ClientFacade {

	// ----------------------Properties--------------------

	private int companyId;

	// -----------------------------G&S-----------------------------------

	/**
	 * getCompanyId() a method that gets the company ID
	 * 
	 * @return the company ID
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * setCompanyId() method that sets the company ID
	 * 
	 * @param companyId company ID to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	// -----------------------------Constructor-----------------------------------

	/**
	 * CompanyFacade constructor gets the email and the password of the Company and
	 * checks if login is possible with this email and password
	 * 
	 * @param email    gets email to check for login details
	 * @param password gets password to check for login details
	 * @throws Exception can throws Exception
	 */
	public CompanyFacade(String email, String password) throws Exception {
		if (!login(email, password)) {
			throw new Exception("Company log in failed- please try again");
		} else {
			// setting the company ID for the use of the methods
			setCompanyId(companiesDBDAO.getCompanyIdByEmailAndPassword(email, password));
		}

	}

	// ----------------------------Methods-----------------------------------

	/**
	 * login() method that Override the login method of the LoginManager and
	 * compares the login details of the company to the details in the DB to see if
	 * the details are correct
	 * 
	 * @param email    gets an email to check
	 * @param password gets a password to check
	 * @return return true if login successful, and false if not
	 */
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
	 * addCoupon() a method that adds a coupon to the DB after it checks if the new
	 * coupon title already exist for one of the company coupons coupon title cannot
	 * match a coupon title of the same company' but can be the same of other
	 * company
	 * 
	 * @param coupon new Coupon Object to add to the DB
	 * @throws Exception if company already has this coupon title
	 */
	public void addCoupon(Coupon coupon) throws Exception {
		if (!companiesDBDAO.isCouponTitleExist(coupon.getCompanyId(), coupon.getTitle())) {
			// add the coupon to the DB
			couponsDBDAO.addCoupon(coupon);

		} else {
			throw new Exception("Cannot add coupon - coupon title alreade exist for company");
		}
	}

	/**
	 * updateCoupon() a method that updates a coupon in the DB by the coupon
	 * ID,after it checks if coupon ID exist in DB and that coupon-company-ID isn't
	 * changed. cannot update coupon code and cannot update coupon company code
	 * 
	 * @param coupon new Coupon Object to update
	 * @throws Exception if trying to update company ID
	 */
	public void updateCoupon(Coupon coupon) throws Exception {
		// check if coupon ID exist in the DB
		if (couponsDBDAO.isCouponExistById(coupon.getId())) {
			// check if company ID exist in the DB
			if (companiesDBDAO.isCompanyExistsById(coupon.getCompanyId())) {
				// check if coupons company ID has not changed
				if (coupon.getCompanyId() == couponsDBDAO.getOneCoupon(coupon.getId()).getCompanyId()) {
					// update the coupon in the DB
					couponsDBDAO.updateCoupon(coupon);
				} else {
					throw new Exception("Cannot update coupon  - cannot change coupons company ID");
				}
			} else {
				throw new Exception("Cannot update coupon  - company ID dont exist in DB");
			}
		} else {
			throw new Exception("Cannot update coupon - coupon ID dont exist in DB");
		}
	}

	/**
	 * deleteCoupon() a method that deletes a coupon from the DB, the method also
	 * deletes this coupon from coupon purchase history, after it checks the coupon
	 * ID exist in the DB
	 * 
	 * @param couponIdToDelete the ID of coupon needs to be deleted
	 * @throws Exception if the coupon ID don't exist in DB
	 */
	public void deleteCoupon(int couponIdToDelete) throws Exception {
		if (couponsDBDAO.isCouponExistById(couponIdToDelete)) {
			// delete the coupon from the purchase history
			couponsDBDAO.deleteAllCouponPurchaseByCouponId(couponIdToDelete);
			// delete the coupon from the DB
			couponsDBDAO.deleteCoupon(couponIdToDelete);
		} else {
			throw new Exception("Cannot delete coupon - coupon ID dont exist in DB");
		}
	}

	/**
	 * getCompanyCoupons() a method that gets all the company coupons and info from
	 * the DB
	 * 
	 * @return an Array List of company coupons
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws Exception {

		return companiesDBDAO.getAllCompanyCoupons(getCompanyId());
	}

	/**
	 * getCompanyCouponsByCategory() a method that gets all the company coupons that
	 * are from a specific category
	 * 
	 * @param category the specific category to return coupons for
	 * @return an Array List of company category coupons
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) throws Exception {

		return companiesDBDAO.getAllCompanyCouponsByCategory(getCompanyId(), category);
	}

	/**
	 * getCompanyCouponsByMaxPrice() a method that gets all the company coupons that
	 * are up to a maximum price
	 * 
	 * @param maxPrice - the maximum price of the coupons to return
	 * @return an Array List of company coupons up to max price
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws Exception {

		return companiesDBDAO.getAllCompanyCouponsByMaxPrice(companyId, maxPrice);
	}

	/**
	 * getCompanyDetails() a method that gets back the company that is logged in
	 * details
	 * 
	 * @return an Company Object of the registered company
	 * @throws Exception can throws Exception
	 */
	public Company getCompanyDetails() throws Exception {
		return companiesDBDAO.getOneCompany(getCompanyId());

	}

}

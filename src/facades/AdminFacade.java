package facades;

import java.util.ArrayList;

import javaBeans.Company;
import javaBeans.Customer;

/**
 * AdminFacade This is a facade class of the administrator. it contains all the
 * actions the administrator of the system can do. this class extends
 * ClientFacade and Override it's login method
 * 
 * @author dan
 *
 */
public class AdminFacade extends ClientFacade {

	// -------------------Constructor------------------

	/**
	 * AdminFacade constructor gets the email and the password of the Admin and
	 * checks if login is possible with this email and password
	 * 
	 * @param email    gets email to check for login details
	 * @param password gets password to check for login details
	 * @throws Exception can throws Exception
	 */
	public AdminFacade(String email, String password) throws Exception {
		if (!login(email, password)) {
			throw new Exception("Faild loggin-in as an Administrator, please enter correct Admin details");
		}

	}

	// -------------------Methods------------------

	/**
	 * login() method that Override the login method of the LoginManager and
	 * compares the login details of the Administrator to the hard-coded
	 * "admin@admin.com" and "admin" to see if details are correct
	 * 
	 * @param email    gets an email to check
	 * @param password gets a password to check
	 * @return return true if login successful, and false if not
	 */
	@Override
	public boolean login(String email, String password) {

		if (email.equals("admin@admin.com") && password.equals("admin")) {

			System.out.println("Admin Login Successful");
			return true;
		} else {
			return false;
		}

	}

	/**
	 * addCompany() a method that adds a company to the DB after it checks if new
	 * company email or name already exists in the DB. cannot add a company with a
	 * name that already exist in the DB. cannot add a company with a Email that
	 * already exist in the DB
	 * 
	 * @param company new company Object
	 * @throws Exception if company name or email already exists in DB.
	 */
	public void addCompany(Company company) throws Exception {

		if (!companiesDBDAO.isCompanyEmailExists(company.getEmail())) {
			if (!companiesDBDAO.isCompanyNameExists(company.getName())) {
				companiesDBDAO.addCompany(company);
				System.out.println("Company " + company.getName() + " has been added");
			} else {
				throw new Exception("Cannot add company, " + company.getName() + " as a company name is already taken");
			}
		} else {
			throw new Exception("Cannot add company, " + company.getEmail() + " as a company Email is already taken");
		}
	}

	/**
	 * updateCompany() a method that updates company details in the DB after it
	 * checks if company exists in DB by ID, and that company name hasen't changed.
	 * cannot update company Name
	 * 
	 * @param company company Object to update
	 * @throws Exception if company dosen't exist or trying to change company name.
	 */
	public void updateCompany(Company company) throws Exception {

		if (companiesDBDAO.isCompanyExistsById(company.getId())) {
			if (companiesDBDAO.getOneCompany(company.getId()).getName().equals(company.getName())) {
				companiesDBDAO.updateCompany(company);
				System.out.println("Company " + company.getName() + " has been updated");
			} else {
				throw new Exception("You cannot update company name");
			}

		} else {
			throw new Exception("Cannot update company  company ID dosent exist in DB");
		}

	}

	/**
	 * deleteCompany() a method that deletes company from DB if the company exist in
	 * the DB. in addition it deletes company coupons and coupons from history
	 * purchases
	 * 
	 * @param companyId company ID to delete
	 * @throws Exception can throws Exception
	 */
	public void deleteCompany(int companyId) throws Exception {

		if (companiesDBDAO.isCompanyExistsById(companyId)) {
			couponsDBDAO.deleteAllCouponPurchaseByCompanyId(companyId);
			couponsDBDAO.deleteAllCompanyCoupons(companyId);
			companiesDBDAO.deleteCompany(companyId);
		} else {
			throw new Exception("Compony to delete does not exists");
		}
	}

	/**
	 * getAllCompanies() a method that gets all companies list and info from DB
	 * 
	 * @return an array list of the companies
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Company> getAllCompanies() throws Exception {

		return companiesDBDAO.getAllCompanies();

	}

	/**
	 * getOneCompany() a method that gets one company by its ID
	 * 
	 * @param companyId company ID to get
	 * @return a company Object
	 * @throws Exception can throws Exception
	 */
	public Company getOneCompany(int companyId) throws Exception {
		if (companiesDBDAO.isCompanyExistsById(companyId)) {
			return companiesDBDAO.getOneCompany(companyId);
		} else {
			System.out.println("Company ID does not exist in DB");
			return null;
		}
	}

	/**
	 * addCustomer() a method that adds a customer to the DB if the Email is not in
	 * the DB of customers
	 * 
	 * @param newCustomer company Object to add
	 * @throws Exception if other customer has the same email
	 */
	public void addCustomer(Customer newCustomer) throws Exception {

		if (!customersDBDAO.isCustomerEmailExists(newCustomer.getEmail())) {

			customersDBDAO.addCustomer(newCustomer);

		} else {
			throw new Exception("Cannot add customer, Email already exists");
		}

	}

	/**
	 * updateCustomer() a method that updates customer details by its ID so the ID
	 * can't be updated, after checking if it is exist
	 * 
	 * @param customerUpdate customer Object to update
	 * @throws Exception if customer does not exist
	 */
	public void updateCustomer(Customer customerUpdate) throws Exception {

		if (customersDBDAO.isCustomerExistsById(customerUpdate.getId())) {

			customersDBDAO.updateCustomer(customerUpdate);
		} else {
			throw new Exception("Cannot update customer- customer ID dosent exist in DB");
		}

	}

	/**
	 * deleteCustomer() a method that deletes customer from DB it also deletes the
	 * customer coupons from history purchases, after checking if the customer exist
	 * 
	 * @param customerIdToDelete the customer ID needs to delete
	 * @throws Exception can throws Exception
	 */
	public void deleteCustomer(int customerIdToDelete) throws Exception {
		if (customersDBDAO.isCustomerExistsById(customerIdToDelete)) {
			couponsDBDAO.deleteAllCouponPurchaseByCustomerId(customerIdToDelete);
			customersDBDAO.deleteCustomer(customerIdToDelete);
		} else {
			throw new Exception("Customer to delete does not exists");
		}

	}

	/**
	 * getAllCustomers() a method that gets all the customers list and info from DB
	 * 
	 * @return an array list of the customers
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Customer> getAllCustomers() throws Exception {
		return customersDBDAO.getAllCustomers();

	}

	/**
	 * getOneCustomer() a method that gets one customer by its ID if it exist
	 * 
	 * @param customerId customer ID to get
	 * @return a customer Object
	 * @throws Exception can throws Exception
	 */
	public Customer getOneCustomer(int customerId) throws Exception {
		if (customersDBDAO.isCustomerExistsById(customerId)) {
			return customersDBDAO.getOneCustomer(customerId);
		} else {
			throw new Exception("Coustomer ID does not exist in DB");

		}
	}

}

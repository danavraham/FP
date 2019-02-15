package facades;

import java.util.ArrayList;

import dbdao.CompaniesDBDAO;
import dbdao.ConnectionPool;
import javaBeans.Company;
import javaBeans.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminFacade extends ClientFacade {

	// --------------------------------CTOR----------------------------
	public AdminFacade() {
		super();

	}

	@Override
	public boolean login(String email, String password) {

		if (email == "admin@admin.com" && password == "admin") {

			System.out.println("Admin Login Successful");
			return true;
		} else {
			System.out.println("Login failed, please enter correct Admin details");
			return false;
		}

	}

	/**
	 * addCompany() - a method that adds a company to the DB after it checks if new company
	 * email on name already exists in the DB
	 * @param company - new company Object
	 * @throws Exception - if company name or email already exists.
	 */
	public void addCompany(Company company) throws Exception {

		if (!companiesDBDAO.isCompanyEmailExists(company.getName())) {
			if (!companiesDBDAO.isCompanyNameExists(company.getEmail())) {
				companiesDBDAO.addCompany(company);
				System.out.println(company.getName() + " has been added");
			} else {
				throw new Exception(company.getName() + " as a company name is already taken");
			}
		} else {
			throw new Exception(company.getEmail() + " as a company Email is already taken");
		}
	}

	
	/**
	 * * updateCompany() - a method that updates company details in the DB after it checks if 
	 *company exists in DB by ID, and that company name hasen't changed
	 * @param company - company Object to update
	 * @throws Exception - if company dosen't exist or trying to change company name.
	 */
	public void updateCompany(Company company) throws Exception {

		if (companiesDBDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
			if (companiesDBDAO.getOneCompany(company.getId()).getName() == company.getName()) {
				companiesDBDAO.updateCompany(company);
			} else {
				System.out.println("You cannot update company name");
			}

		} else {
			System.out.println("cannot update company that dosent exist in DB");
		}

	}

	
	/**
	 * deleteCompany() - a method that deletes company from DB 
	 * and deletes company coupons and coupons from history purchases
	 * @param companyId - company ID to delete
	 * @throws Exception
	 */
	public void deleteCompany(int companyId) throws Exception {
		
		couponsDBDAO.deleteAllCouponPurchaseByCompanyId(companyId);
		couponsDBDAO.deleteAllCompanyCoupons(companyId);
		companiesDBDAO.deleteCompany(companyId);
	}

	/**
	 * getAllCompanies() - a method that gets all companies list from DB
	 * @return an array list of the companies
	 * @throws Exception
	 */
	public ArrayList<Company> getAllCompanies() throws Exception {
		
		return companiesDBDAO.getAllCompanies();

	}

	/**
	 * getOneCompany() - a method that gets one company by its ID 
	 * @param companyId - company ID to get
	 * @return a company Object
	 * @throws Exception 
	 */
	public Company getOneCompany(int companyId) throws Exception {
		
		return companiesDBDAO.getOneCompany(companyId);
	}

	
	
	/**
	 * addCustomer() - a method that adds a customer to the DB if the Email is not in the DB of customers
	 * @param newCustomer - company Object to add
	 * @throws Exception if other customer has the same email
	 */
	public void addCustomer(Customer newCustomer) throws Exception {
		
		if (customersDBDAO.isCustomerEmailExists(newCustomer.getEmail())) {
			
				customersDBDAO.addCustomer(newCustomer);
				System.out.println(newCustomer.getFirstName()+" " +newCustomer.getLastName() + " was added");
			} else {
				throw new Exception("Cannot add, Email already exists");
			}
		

	}

	/**
	 * updateCustomer() - a method that updates customer details by its ID after checking if its Email and Password exists 
	 * @param customerUpdate - customer Object to update
	 * @throws Exception if customer does not exist
	 */
	public void updateCustomer(Customer customerUpdate) throws Exception {
		
            if (customersDBDAO.isCustomerExists(customerUpdate.getEmail(), customerUpdate.getPassword())) {
            	customersDBDAO.updateCustomer(customerUpdate);
            }else {
            	System.out.println("This customer does not exist");
            }

	}

	/**
	 * deleteCustomer() - a method that deletes customer from DB 
	 * and deletes the customer coupons from history purchases
	 * @param customerIdToDelete
	 * @throws Exception
	 */
	public void deleteCustomer(int customerIdToDelete) throws Exception {
		
		couponsDBDAO.deleteAllCouponPurchaseByCustomerId(customerIdToDelete);
		customersDBDAO.deleteCustomer(customerIdToDelete);
	}

	/**
	 *  getAllCustomers() - a method that gets all the customers list from DB
	 * @return an array list of the customers
	 * @throws Exception 
	 */
	public ArrayList<Customer> getAllCustomers() throws Exception {
		return customersDBDAO.getAllCustomers();

	}

	/**
	 * getOneCustomer() - a method that - a method that gets one customer by its ID
	 * @param customerId - customer ID to get
	 * @return a customer Object
	 * @throws Exception 
	 */
	public Customer getOneCustomer(int customerId) throws Exception {
		return customersDBDAO.getOneCustomer(customerId);

	}

}

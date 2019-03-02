package facades;

import dao.*;
import dbdao.*;

/**
 * ClientFacade the abstract class which the client types facades will implement
 * through the LoginManager
 * 
 * @author dan
 *
 */
public abstract class ClientFacade {

	ICompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	ICustomersDAO customersDBDAO = new CustomerDBDAO();
	ICouponsDAO couponsDBDAO = new CouponsDBDAO();

	/**
	 * login() the abstract method that through the implementation will return true
	 * if login successful
	 * 
	 * @param email    gets a client email for login
	 * @param password gets a client password for login
	 * @return by default false, if login is successful through implementation- will
	 *         return true
	 * @throws Exception can throws Exception
	 */
	public boolean login(String email, String password) throws Exception {
		return false;

	}
}

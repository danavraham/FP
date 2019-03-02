package facades;

import dao.ICompaniesDAO;
import dao.ICustomersDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CustomerDBDAO;

/**
 * LoginManager is a Singleton class that allows 3 type of clients to login to
 * the system
 * 
 * @author dan
 */
public class LoginManager {

	// -------------------------Properties-----------------

	ICompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	ICustomersDAO customersDBDAO = new CustomerDBDAO();

	private static LoginManager instance = new LoginManager();

	/**
	 * Private empty constructor suppresses generation of a (public) default
	 * constructor
	 */
	private LoginManager() {
	}

	/**
	 * @return returns a reference to the instance for the LoginManager Class.
	 */
	public static LoginManager getInstance() {
		return instance;

	}

	/**
	 * login() a method that create the login for one of 3 client types
	 * 
	 * @param email      the email of the client trying to login
	 * @param password   the password of the client trying to login
	 * @param clientType the client type(Enum) of the client trying to log in
	 * @return the right client facade if the login details are correct
	 * @throws Exception can throws Exception
	 */
	public ClientFacade login(String email, String password, ClientType clientType) throws Exception {

		if (clientType.equals(ClientType.Administrator)) {
			return new AdminFacade(email, password);

		} else if (clientType.equals(ClientType.Company)) {
			return new CompanyFacade(email, password);

		} else if (clientType.equals(ClientType.customer)) {
			return new CustomerFacade(email, password);

		} else
			return null;

	}

}

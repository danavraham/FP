package facades;

import dao.ICompaniesDAO;
import dao.ICustomersDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CustomerDBDAO;

public class LoginManager {

	// -------------------------Properties-----------------

	ICompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	ICustomersDAO customersDBDAO = new CustomerDBDAO();

	private static LoginManager instance = new LoginManager();

	// Private constructor suppresses generation of a (public) default constructor
	private LoginManager() {
	}

	// Public static method that returns a reference to the instance.
	public static LoginManager getInstance() {
		return instance;

	}

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

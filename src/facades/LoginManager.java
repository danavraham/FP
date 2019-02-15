package facades;

import dao.ICompaniesDAO;
import dao.ICustomersDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CustomerDBDAO;

public class LoginManager {

	// -------------------------Properties-----------------

	ICompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	ICustomersDAO customersDBDAO = new CustomerDBDAO();

	private static LoginManager INSTANCE = new LoginManager();

	// Private constructor suppresses generation of a (public) default constructor
	private LoginManager() {
	}

	// Public static method that returns a reference to the instance.
	public static LoginManager getInstance() {
		return INSTANCE;

	}

	public ClientFacade login(String email, String password, ClientType clientType) throws Exception {

		if (clientType == ClientType.Administrator) {
			if (email == "admin@admin.com" && password == "admin") {
				return new AdminFacade();
			}

		} else if (clientType == ClientType.Company) {
			if (companiesDBDAO.isCompanyExists(email, password)) {
				return new CompanyFacade();
			}

		} else if (clientType == ClientType.customer) {
			if (customersDBDAO.isCustomerExists(email, password)) {
				return new CompanyFacade();
			}
		}
		return null;

	}

}

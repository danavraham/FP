package facades;

import dao.*;
import dbdao.*;

public abstract class ClientFacade {

	ICompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	ICustomersDAO customersDBDAO = new CustomerDBDAO();
	ICouponsDAO couponsDBDAO = new CouponsDBDAO();

	public boolean login(String email, String password) {

		return false;

	}
}

package dataBase;

import java.util.ArrayList;
import javaBeans.Company;

public interface ICompaniesDAO {

	boolean isCompanyExists(String email, String Password)throws Exception;
    void addCompany(Company addCompany) throws Exception;
    void updateCompany(Company updateCompany) throws Exception;   
    void deleteCompany(int deleteCompanyByID) throws Exception;   
	ArrayList<Company> getAllCompanies() throws Exception;
    Company getOneCompany(int getCompanyByID) throws Exception;

}
package dataBase;

import java.util.ArrayList;
import javaBeans.Customer;

public interface ICustomersDAO {

	boolean isCustomerExists(String email, String Password)throws Exception;
    void addCustomer(Customer addCustomer) throws Exception;
    void updateCustomer(Customer updateCustomer) throws Exception;   
    void deleteCustomer(int deleteCustomerByID) throws Exception;   
	ArrayList<Customer> getAllCustomers() throws Exception;
	Customer getOneCustomer(int getCustomerByID) throws Exception;

}
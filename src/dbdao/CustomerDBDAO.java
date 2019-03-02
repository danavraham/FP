package dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dao.ICustomersDAO;
import javaBeans.Category;
import javaBeans.Coupon;
import javaBeans.Customer;

/**
 * CustomerDBDAO the class that implements the CustomerDAO, with all the methods
 * the Customer class will use. this class create the changes in the DB through
 * the connectionPool
 * 
 * @author dan
 *
 */
public class CustomerDBDAO implements ICustomersDAO {

	/**
	 * an instance of the ConnectionPool
	 */
	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * isCustomerExists() a method that gets the customer email and password and
	 * checks if the customer exist in the DB
	 * 
	 * @param email    getting the customer email
	 * @param Password getting the customer password
	 * @return True- if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	public boolean isCustomerExists(String email, String password) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM CUSTOMERS WHERE customer_email = '%s' AND customer_password = '%s' ",
					email, password);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * isCustomerExistsById() a method that gets the customer ID and checks if the
	 * customer ID exist in the DB
	 *
	 * @param customerId customerId to check
	 * @return True- if customer ID exist in the DB, False- if the customer ID dose
	 *         not exist
	 * @throws Exception can throws Exception
	 */
	public boolean isCustomerExistsById(int customerId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM CUSTOMERS WHERE customer_id = '%d'", customerId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * isCustomerEmailExists() a method that gets the customer email and checks if
	 * the customer email exist in the DB
	 * 
	 * @param email    getting the customer email
	 * @param Password getting the customer password
	 * @return True- if customer exist in the DB, False- if the customer dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	public boolean isCustomerEmailExists(String email) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM CUSTOMERS WHERE customer_email = '%s'", email);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * addCustomer() a method that adds a customer to the customers table in the DB
	 * 
	 * @param addCustomer a customer object to add
	 * @throws Exception can throws Exception
	 */
	public void addCustomer(Customer addCustomer) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"INSERT INTO CUSTOMERS(first_name, last_name, customer_email, customer_password) "
							+ "VALUES('%s', '%s', '%s', '%s')",
					addCustomer.getFirstName(), addCustomer.getLastName(), addCustomer.getEmail(),
					addCustomer.getPassword());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					addCustomer.setId(id);
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Customer " + addCustomer.getFirstName() + " " + addCustomer.getLastName() + " was added");

	}

	/**
	 * updateCustomer() a method that updates customer info in the customers table
	 * in the DB
	 * 
	 * @param updateCustomer - a customer object to update
	 * @throws Exception can throws Exception
	 */
	public void updateCustomer(Customer updateCustomer) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE CUSTOMERS SET first_name='%s', last_name='%s', customer_email='%s', customer_password='%s' WHERE customer_id=%d",
					updateCustomer.getFirstName(), updateCustomer.getLastName(), updateCustomer.getEmail(),
					updateCustomer.getPassword(), updateCustomer.getId());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Customer was updated");

	}

	/**
	 * deleteCustomer() a method that deletes a customer from the customers table in
	 * the DB by customer ID
	 * 
	 * @param deleteCustomerByID a customer ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	public void deleteCustomer(int deleteCustomerByID) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM CUSTOMERS WHERE customer_id=%d", deleteCustomerByID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Customer was deleted");
	}

	/**
	 * getOneCustomer() a method that gets a single customer info from DB by
	 * customer ID
	 * 
	 * @param getCustomerByID a customer ID to get the info of
	 * @return a Customer object from DB with all the customer data
	 * @throws Exception can throws Exception
	 */
	public Customer getOneCustomer(int getCustomerByID) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			String sql = String.format("SELECT * FROM CUSTOMERS WHERE customer_id=%d", getCustomerByID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String firstName = resultSet.getString("first_name");
					String lastName = resultSet.getString("last_name");
					String email = resultSet.getString("customer_email");
					String password = resultSet.getString("customer_password");
					ArrayList<Coupon> coupons = getAllCustomerCoupons(getCustomerByID);

					Customer customer = new Customer(getCustomerByID, email, password, firstName, lastName, coupons);
					return customer;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getAllCustomers() a method that gets all the customers info from the DB
	 * 
	 * @return an ArrayList of all the customers and their data in the DB
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Customer> getAllCustomers() throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM CUSTOMERS";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Customer> allCustomers = new ArrayList<Customer>();

					while (resultSet.next()) {

						int id = resultSet.getInt("customer_id");
						String firstName = resultSet.getString("first_name");
						String lastName = resultSet.getString("last_name");
						String email = resultSet.getString("customer_email");
						String password = resultSet.getString("customer_password");
						ArrayList<Coupon> coupons = new ArrayList<>();

						// insert the coupons the customer have from couponsDBDAO
						Customer customer = new Customer(id, email, password, firstName, lastName, coupons);

						allCustomers.add(customer);
					}

					return allCustomers;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * isCouponExistForCustomer() a method that checks if the customer already
	 * purchased a specific coupon already
	 * 
	 * @param customerId customer Id to check coupons
	 * @param couponId   coupon id to check if already been purchased by customer
	 * @return True- if already purchased this coupon, False- if not
	 * @throws Exception can throws Exception
	 */
	public boolean isCouponExistForCustomer(int customerId, int couponId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM customers_vs_coupons WHERE customer_id = '%d' AND coupon_id = '%d'",
					customerId, couponId);
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");
					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

	}

	/**
	 * getAllCustomerCoupons() a method that returns all the purchased coupons of a
	 * specific customer
	 * 
	 * @param customerId customer id to get the coupons of
	 * @return ArrayList of coupons purchased by customer
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql1 = String.format("SELECT * FROM CUSTOMERS_VS_COUPONS WHERE customer_id=%d", customerId);
			String sql2 = String.format("SELECT * FROM COUPONS");

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {

					try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
						try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
							ArrayList<Integer> CouponsIdForCustomer = new ArrayList<Integer>();
							ArrayList<Coupon> customerCouponList = new ArrayList<Coupon>();
							
							//getting all the customer purchased coupon IDs history from CUSTOMERS_VS_COUPONS and adding to an ArrayList
							while (resultSet1.next()) {

								int couponId = resultSet1.getInt("coupon_id");

								CouponsIdForCustomer.add(couponId);
							}
							//finding the coupons from the ArrayList in the COUPONS table
							while (resultSet2.next()) {

								for (int i : CouponsIdForCustomer) {

									if (i == resultSet2.getInt("coupon_id")) {

										int couponId = resultSet2.getInt("coupon_id");
										int companyId = resultSet2.getInt("company_id");
										int categoryId = resultSet2.getInt("category_id");
										String title = resultSet2.getString("title");
										String description = resultSet2.getString("description");
										Date startDate = resultSet2.getDate("start_date");
										Date endDate = resultSet2.getDate("end_date");
										int amount = resultSet2.getInt("amount");
										double price = resultSet2.getDouble("price");
										String image = resultSet2.getString("image");

										Coupon coupon = new Coupon(couponId, companyId,
												CouponsDBDAO.categoryIdToString(categoryId), title, description,
												startDate, endDate, amount, price, image);

										customerCouponList.add(coupon);
									}
								}

							}
							return customerCouponList;
						}
					}
				}
			}

		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * getAllCustomerCouponsByCategory() a method that returns all the specific
	 * customer purchased coupons from specific category
	 * 
	 * @param customerId customer id to check coupons of
	 * @param categoryId the category of coupons to return
	 * @return ArrayList of coupons purchased by customer for specific category
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCustomerCouponsByCategory(int customerId, Category category) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql1 = String.format("SELECT * FROM CUSTOMERS_VS_COUPONS WHERE customer_id=%d", customerId);
			String sql2 = String.format("SELECT * FROM COUPONS");

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {

					try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
						try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {

							ArrayList<Integer> CouponsIdForCustomer = new ArrayList<Integer>();
							ArrayList<Coupon> customerCouponList = new ArrayList<Coupon>();

							while (resultSet1.next()) {

								int couponId = resultSet1.getInt("coupon_id");

								CouponsIdForCustomer.add(couponId);
							}

							while (resultSet2.next()) {

								for (int i : CouponsIdForCustomer) {

									if (i == resultSet2.getInt("coupon_id") && resultSet2
											.getInt("category_id") == CouponsDBDAO.categoryStringToId(category)) {

										int couponId = resultSet2.getInt("coupon_id");
										int companyId = resultSet2.getInt("company_id");
										int categorySingleId = resultSet2.getInt("category_id");
										String title = resultSet2.getString("title");
										String description = resultSet2.getString("description");
										Date startDate = resultSet2.getDate("start_date");
										Date endDate = resultSet2.getDate("end_date");
										int amount = resultSet2.getInt("amount");
										double price = resultSet2.getDouble("price");
										String image = resultSet2.getString("image");

										Coupon coupon = new Coupon(couponId, companyId,
												CouponsDBDAO.categoryIdToString(categorySingleId), title, description,
												startDate, endDate, amount, price, image);

										customerCouponList.add(coupon);
									}
								}

							}
							return customerCouponList;
						}
					}
				}
			}

		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * getAllCustomerCouponsByMaxPrice() a method that returns all the specific
	 * customer purchased coupons up to a max price
	 * 
	 * @param customerId customer id to check coupons of
	 * @param maxPrice   the maximum price of coupons that will be returned
	 * @return ArrayList of coupons purchased by customer up to a max price
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCustomerCouponsByMaxPrice(int customerId, int maxPrice) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql1 = String.format("SELECT * FROM CUSTOMERS_VS_COUPONS WHERE customer_id=%d", customerId);
			String sql2 = String.format("SELECT * FROM COUPONS");

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {

					try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
						try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {

							ArrayList<Integer> CouponsIdForCustomer = new ArrayList<Integer>();
							ArrayList<Coupon> customerCouponList = new ArrayList<Coupon>();

							while (resultSet1.next()) {

								int couponId = resultSet1.getInt("coupon_id");

								CouponsIdForCustomer.add(couponId);
							}

							while (resultSet2.next()) {

								for (int i : CouponsIdForCustomer) {

									if (i == resultSet2.getInt("coupon_id")
											&& resultSet2.getDouble("price") <= maxPrice) {

										int couponId = resultSet2.getInt("coupon_id");
										int companyId = resultSet2.getInt("company_id");
										int categorySingleId = resultSet2.getInt("category_id");
										String title = resultSet2.getString("title");
										String description = resultSet2.getString("description");
										Date startDate = resultSet2.getDate("start_date");
										Date endDate = resultSet2.getDate("end_date");
										int amount = resultSet2.getInt("amount");
										double price = resultSet2.getDouble("price");
										String image = resultSet2.getString("image");

										Coupon coupon = new Coupon(couponId, companyId,
												CouponsDBDAO.categoryIdToString(categorySingleId), title, description,
												startDate, endDate, amount, price, image);

										customerCouponList.add(coupon);
									}
								}

							}
							return customerCouponList;
						}
					}
				}
			}

		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * getCustomerIdByEmailAndPassword() a method that returns a customer ID by
	 * getting its email and password
	 * 
	 * @param email    customer email to check
	 * @param password customer password to check
	 * @return ID of the specific customer
	 * @throws Exception can throws Exception
	 */
	public int getCustomerIdByEmailAndPassword(String email, String password) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT * FROM CUSTOMERS WHERE customer_email = '%s' AND customer_password = '%s'", email,
					password);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();
					int id = resultSet.getInt("customer_id");
					return id;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

	}

}
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

public class CustomerDBDAO implements ICustomersDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
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

	@Override
	public boolean isCustomerEmailExists(String email) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM CUSTOMERS WHERE customer_email = '%s'",
					email);

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

	@Override
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

			System.out.println("Customer was added");
		
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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
					ArrayList<Coupon> coupons = new ArrayList<Coupon>();

					Customer customer = new Customer(getCustomerByID, email, password, firstName, lastName, coupons);
					return customer;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public boolean isCouponExistForCustomer(int customerId, int couponId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM COUPONS WHERE customer_id = '%d' AND coupon_id = '%d'", customerId,
					couponId);

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

	@Override
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

							while (resultSet1.next()) {

								int couponId = resultSet1.getInt("coupon_id");

								CouponsIdForCustomer.add(couponId);
							}

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
										double price = resultSet2.getInt("price");
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

	
		
	
	@Override
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
	
									if (i == resultSet2.getInt("coupon_id") && resultSet2.getInt("category_id") == CouponsDBDAO.categoryStringToId(category) ) {
						
										int couponId = resultSet2.getInt("coupon_id");
										int companyId = resultSet2.getInt("company_id");
										int categorySingleId = resultSet2.getInt("category_id");
										String title = resultSet2.getString("title");
										String description = resultSet2.getString("description");
										Date startDate = resultSet2.getDate("start_date");
										Date endDate = resultSet2.getDate("end_date");
										int amount = resultSet2.getInt("amount");
										double price = resultSet2.getInt("price");
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

	@Override
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

									if (i == resultSet2.getInt("coupon_id") && resultSet2.getInt("price") <= maxPrice) {
						
										int couponId = resultSet2.getInt("coupon_id");
										int companyId = resultSet2.getInt("company_id");
										int categorySingleId = resultSet2.getInt("category_id");
										String title = resultSet2.getString("title");
										String description = resultSet2.getString("description");
										Date startDate = resultSet2.getDate("start_date");
										Date endDate = resultSet2.getDate("end_date");
										int amount = resultSet2.getInt("amount");
										double price = resultSet2.getInt("price");
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
}
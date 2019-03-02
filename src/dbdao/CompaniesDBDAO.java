package dbdao;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.ICompaniesDAO;
import javaBeans.Category;
import javaBeans.Company;
import javaBeans.Coupon;

/**
 * CompaniesDBDAO the class that implements the CompaniesDAO, with all the
 * methods the Company class will use. this class create the changes in the DB
 * through the connectionPool
 * 
 * 
 * @author dan
 *
 */
public class CompaniesDBDAO implements ICompaniesDAO {

	/**
	 * an instance of the ConnectionPool
	 */
	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * isCompanyExists() a method that gets the company email and password and
	 * checks if the company exist in the DB
	 * 
	 * @param email    getting the company email
	 * @param password getting the company password
	 * @return True if company exist in the DB, False- if the company dose not exist
	 * @throws Exception can throws Exception
	 */
	public boolean isCompanyExists(String email, String password) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM COMPANIES WHERE company_email = '%s' AND company_password = '%s'",
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
	 * isCompanyNameExists() a method that checks if company name is already in the
	 * DB
	 * 
	 * @param companyName company name to check
	 * @return True if company name already in the DB, False if dosen't
	 * @throws Exception can throws Exception
	 */
	public boolean isCompanyNameExists(String companyName) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COMPANIES WHERE company_name = '%s'",
					companyName);

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
	 * isCompanyEmailExists() a method that checks if company email is already in
	 * the DB
	 * 
	 * @param email company email to check
	 * @return True if company email already in the DB, False if dosen't
	 * @throws Exception can throws Exception
	 */
	public boolean isCompanyEmailExists(String email) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COMPANIES WHERE company_email = '%s'", email);

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
	 * isCompanyExistsById() a method that gets the company ID and checks if the
	 * company ID exist in the DB
	 * 
	 * @param companyId getting the company ID
	 * @return True if company ID exist in the DB, False- if the company ID dose not
	 *         exist
	 * @throws Exception can throws Exception
	 */
	public boolean isCompanyExistsById(int companyId) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COMPANIES WHERE company_id = '%d'", companyId);

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
	 * addCompany() a method that adds a company to the companies table in the DB
	 * 
	 * @param addCompany a Company object to add to the DB
	 * @throws Exception can throws Exception
	 */
	public void addCompany(Company addCompany) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();
			String sql = String.format(
					"INSERT INTO COMPANIES(company_name, company_email, company_password) "
							+ "VALUES('%s', '%s', '%s')",
					addCompany.getName(), addCompany.getEmail(), addCompany.getPassword());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					addCompany.setId(id); // Add the new created id into the company object.
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

	}

	/**
	 * updateCompany() a method that updates company info in the companies table in
	 * the DB by its ID
	 * 
	 * @param updateCompany a Company object to be update
	 * @throws Exception can throws Exception
	 */
	public void updateCompany(Company updateCompany) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE COMPANIES SET company_name='%s', company_email='%s', company_password='%s' WHERE company_id=%d",
					updateCompany.getName(), updateCompany.getEmail(), updateCompany.getPassword(),
					updateCompany.getId());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

	}

	/**
	 * deleteCompany() a method that deletes a company from the companies table in
	 * the DB by company ID
	 * 
	 * @param deleteCompanyByID a company ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	public void deleteCompany(int deleteCompanyByID) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM COMPANIES WHERE company_id=%d", deleteCompanyByID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Company deleted");
	}

	/**
	 * getOneCompany() a method that gets a single company info from DB by company
	 * ID
	 * 
	 * @param companyID a company ID to get the info of
	 * @return a Company object from DB with all the company data
	 * @throws Exception can throws Exception
	 */
	public Company getOneCompany(int companyID) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COMPANIES WHERE company_id=%d", companyID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String name = resultSet.getString("company_name");
					String email = resultSet.getString("company_email");
					String password = resultSet.getString("company_password");
					ArrayList<Coupon> coupons = getAllCompanyCoupons(companyID);

					Company company = new Company(companyID, email, password, name, coupons);
					return company;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getCompanyIdByEmailAndPassword() a method that gets a single company info
	 * from DB by the company Email and Password
	 * 
	 * @param email    a company email to get the info of
	 * @param password a company password to get the info of
	 * @return a Company object from DB with all the company data
	 * @throws Exception can throws Exception
	 */
	public int getCompanyIdByEmailAndPassword(String email, String password) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COMPANIES WHERE company_email = '%s' AND company_password = '%s'",
					email, password);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int id = resultSet.getInt("company_id");

					return id;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

	}

	/**
	 * getAllCompanies() a method that gets all the companies info from the DB
	 * 
	 * @return an ArrayList of all the companies and their data in the DB
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Company> getAllCompanies() throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM COMPANIES";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Company> companyList = new ArrayList<Company>();

					while (resultSet.next()) {

						int id = resultSet.getInt("company_id");
						String email = resultSet.getString("company_email");
						String password = resultSet.getString("company_password");
						String name = resultSet.getString("company_name");
						ArrayList<Coupon> coupons = getAllCompanyCoupons(id);

						Company company = new Company(id, email, password, name, coupons);

						companyList.add(company);
					}

					return companyList;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getAllCompanyCoupons() a method that returns from the DB all the coupons of
	 * the company
	 * 
	 * @param companyId company ID to get the coupons for
	 * @return an ArrayList of company coupons
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCompanyCoupons(int companyId) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE company_id=%d", companyId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> companyCouponsList = new ArrayList<Coupon>();

					while (resultSet.next()) {

						int couponId = resultSet.getInt("coupon_id");
						int companySingleId = resultSet.getInt("company_id");
						int categoryId = resultSet.getInt("category_id");
						String title = resultSet.getString("title");
						String description = resultSet.getString("description");
						Date startDate = resultSet.getDate("start_date");
						Date endDate = resultSet.getDate("end_date");
						int amount = resultSet.getInt("amount");
						double price = resultSet.getDouble("price");
						String image = resultSet.getString("image");

						Coupon coupon = new Coupon(couponId, companySingleId,
								CouponsDBDAO.categoryIdToString(categoryId), title, description, startDate, endDate,
								amount, price, image);

						companyCouponsList.add(coupon);
					}

					return companyCouponsList;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getAllCompanyCouponsByCategory() a method that returns from the DB all the
	 * coupons for the company that are in a specific Category
	 * 
	 * @param companyId  company ID to get the coupons for
	 * @param category category to get the coupons for
	 * @return an ArrayList of company coupons per specific category
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE company_id='%d' AND category_id = '%d'", companyId,
					CouponsDBDAO.categoryStringToId(category));

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> companyCouponsList = new ArrayList<Coupon>();

					while (resultSet.next()) {

						int couponId = resultSet.getInt("coupon_id");
						int companySingleId = resultSet.getInt("company_id");
						int categorySingleId = resultSet.getInt("category_id");
						String title = resultSet.getString("title");
						String description = resultSet.getString("description");
						Date startDate = resultSet.getDate("start_date");
						Date endDate = resultSet.getDate("end_date");
						int amount = resultSet.getInt("amount");
						double price = resultSet.getDouble("price");
						String image = resultSet.getString("image");

						Coupon coupon = new Coupon(couponId, companySingleId,
								CouponsDBDAO.categoryIdToString(categorySingleId), title, description, startDate,
								endDate, amount, price, image);

						companyCouponsList.add(coupon);
					}

					return companyCouponsList;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getAllCompanyCouponsByMaxPrice() a method that returns from the DB all the
	 * coupons for the company that are less than a specific price
	 * 
	 * @param companyId company ID to get the coupons for
	 * @param maxPrice  the maximum price of company coupons to return
	 * @return an ArrayList of company coupons up to a specific price
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE company_id='%d'", companyId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> companyCouponsList = new ArrayList<Coupon>();

					while (resultSet.next()) {

						if (resultSet.getDouble("price") <= maxPrice) {
							int couponId = resultSet.getInt("coupon_id");
							int companySingleId = resultSet.getInt("company_id");
							int categoryId = resultSet.getInt("category_id");
							String title = resultSet.getString("title");
							String description = resultSet.getString("description");
							Date startDate = resultSet.getDate("start_date");
							Date endDate = resultSet.getDate("end_date");
							int amount = resultSet.getInt("amount");
							double price = resultSet.getDouble("price");
							String image = resultSet.getString("image");

							Coupon coupon = new Coupon(couponId, companySingleId,
									CouponsDBDAO.categoryIdToString(categoryId), title, description, startDate, endDate,
									amount, price, image);

							companyCouponsList.add(coupon);
						}
					}

					return companyCouponsList;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * isCouponTitleExist() a method that checks in the DB if company already have
	 * the same Title for an existing coupon
	 * 
	 * @param companyId   the company ID to check
	 * @param couponTitle the title to check if already exists for the company
	 * @return True if the coupon title already exists for the company, and False if
	 *         it doesn't
	 * @throws Exception can throws Exception
	 */
	public boolean isCouponTitleExist(int companyId, String couponTitle) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COUPONS WHERE company_id = '%d' AND title = '%s'",
					companyId, couponTitle);

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

}

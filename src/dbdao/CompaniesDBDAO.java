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
 * @author dan
 *
 */
public class CompaniesDBDAO implements ICompaniesDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
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

	@Override
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

			System.out.println("New company has been added");
		
	}

	@Override
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

		System.out.println("Company Updated");

	}

	@Override
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

	@Override
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
						ArrayList<Coupon> coupons = new ArrayList<>();

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

	@Override
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
					ArrayList<Coupon> coupons = new ArrayList<>();

					Company company = new Company(companyID, email, password, name, coupons);

					return company;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

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

	@Override
	public boolean isCouponTitleExist(int companyId, String couponTitle) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM COUPONS WHERE company_id = '%d' AND title = '%s'", companyId,
					couponTitle);

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
						double price = resultSet.getInt("price");
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

	@Override
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
						double price = resultSet.getInt("price");
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

	@Override
	public ArrayList<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE company_id='%d'", companyId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> companyCouponsList = new ArrayList<Coupon>();

					while (resultSet.next()) {

						if (resultSet.getInt("price") <= maxPrice) {
							int couponId = resultSet.getInt("coupon_id");
							int companySingleId = resultSet.getInt("company_id");
							int categoryId = resultSet.getInt("category_id");
							String title = resultSet.getString("title");
							String description = resultSet.getString("description");
							Date startDate = resultSet.getDate("start_date");
							Date endDate = resultSet.getDate("end_date");
							int amount = resultSet.getInt("amount");
							double price = resultSet.getInt("price");
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

}

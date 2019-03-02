package dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dao.ICouponsDAO;
import javaBeans.Category;
import javaBeans.Coupon;

/**
 * CouponsDBDAO the class that implements the CouponsDAO, with all the methods
 * the Coupon class will use. this class create the changes in the DB through
 * the connectionPool
 * 
 * 
 * @author dan
 *
 */
public class CouponsDBDAO implements ICouponsDAO {

	/**
	 * an instance of the ConnectionPool
	 */
	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * * categoryIdToString() a method converts the category ID to its Category name
	 * from the Category Enum Class
	 * 
	 * @param CategoryIndex the category ID number to convert to its Category name
	 * @return a Category name represented by the ID
	 */
	public static Category categoryIdToString(int CategoryIndex) {

		switch (CategoryIndex) {

		case 1:

			return Category.Baby;

		case 2:

			return Category.Baking;

		case 3:

			return Category.Dairy;

		case 4:

			return Category.Medical;

		case 5:

			return Category.Pets;

		case 6:

			return Category.Snacks;

		case 7:
			;
			return Category.Restaurants;

		case 8:

			return Category.Spa;

		case 9:

			return Category.Health;

		case 10:

			return Category.Birthday;

		case 11:

			return Category.Vacation;

		case 12:

			return Category.Concerts;

		case 13:

			return Category.Photography;

		case 14:

			return Category.Electronics;

		case 15:

			return Category.Cars;

		default:
			return null;

		}
	}

	/**
	 * 
	 * * categoryStringToId() a method converts the Category name to its ID from the
	 * Category Enum Class
	 * 
	 * @param category the category name to convert to its ID number
	 * @return a Category ID number representing the Category name
	 * @throws Exception can throws Exception
	 */
	public static int categoryStringToId(Category category) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM  CATEGORIES WHERE category_name='%s' ",
					Category.valueOf(category.toString()));

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int id = resultSet.getInt("category_id");
					return id;
				}
			}

		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * isCouponExistById() a method that check if the coupon exist by its ID
	 * 
	 * @param couponId coupon ID to check if exist in DB
	 * @return True if coupon ID exist in the DB, false if not
	 * @throws Exception can throws Exception
	 */
	public boolean isCouponExistById(int couponId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COUPONS WHERE coupon_id = '%d'", couponId);

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
	 * isCouponLeft() a method that check if the coupon has more than 0 amount by
	 * coupon ID
	 * 
	 * @param couponId coupon ID to check if any left
	 * @return True if there is 1 or more coupons left, false if not
	 * @throws Exception can throws Exception
	 */
	public boolean isCouponLeft(int couponId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE coupon_id = '%d'", couponId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					if (resultSet.getInt("amount") > 0) {

						return true;

					} else {

						return false;
					}
				}

			}
		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * addCoupon() a method that adds a coupon to the coupons table in the DB
	 * 
	 * @param addCoupon a Coupon object to add to DB
	 * @throws Exception can throws Exception
	 */
	public void addCoupon(Coupon addCoupon) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"INSERT INTO COUPONS(company_id, category_id, title, description, start_date, end_date, amount, price, image)"
							+ "VALUES('%d', '%d', '%s', '%s', '%s', '%s','%d', '%f', '%s')",
					addCoupon.getCompanyId(), categoryStringToId(addCoupon.getCategory()), addCoupon.getTitle(),
					addCoupon.getDescription(), new java.sql.Date(addCoupon.getStartDate().getTime()),
					new java.sql.Date(addCoupon.getEndDate().getTime()), addCoupon.getAmount(), addCoupon.getPrice(),
					addCoupon.getImage());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					addCoupon.setId(id);
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		System.out.println("Coupon added successfully");
	}

	/**
	 * isCouponExpiered() a method that check if the coupon has expired by its ID
	 * 
	 * @param couponId coupon ID to check if expired
	 * @return True if coupon has expired, False if not
	 * @throws Exception can throws Exception
	 */
	public boolean isCouponExpiered(int couponId) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE coupon_id = '%d'", couponId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					Date thisDate = new Date();

					if (resultSet.getDate("end_date").after(thisDate)) {

						return false;

					} else {

						return true;
					}
				}

			}
		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

	/**
	 * updateCoupon() a method that Updates a coupon in the coupons table in the DB
	 * 
	 * @param updateCoupon a Coupon object to be update
	 * @throws Exception can throws Exception
	 */
	public void updateCoupon(Coupon updateCoupon) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE COUPONS SET company_id= '%d',category_id= '%d', title='%s', description='%s', start_date='%s', end_date='%s', amount='%d', price='%f', image='%s' WHERE coupon_id=%d",
					updateCoupon.getCompanyId(), categoryStringToId(updateCoupon.getCategory()),
					updateCoupon.getTitle(), updateCoupon.getDescription(),
					new java.sql.Date(updateCoupon.getStartDate().getTime()),
					new java.sql.Date(updateCoupon.getEndDate().getTime()), updateCoupon.getAmount(),
					updateCoupon.getPrice(), updateCoupon.getImage(), updateCoupon.getId());

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Coupon was updated");
	}

	/**
	 * deleteCoupon() a method that deletes a coupon from the coupons table in the
	 * DB by coupon ID
	 * 
	 * @param deleteCouponByID a coupon ID thats needs to be deleted
	 * @throws Exception can throws Exception
	 */
	public void deleteCoupon(int deleteCouponByID) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM COUPONS WHERE coupon_id=%d", deleteCouponByID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Coupon was deleted");
	}

	/**
	 * deleteAllCompanyCoupons() a method that deletes all company coupons from the
	 * coupons table in the DB by company ID
	 * 
	 * @param companyId a company ID thats needs to be delete its coupons
	 * @throws Exception can throws Exception
	 */
	public void deleteAllCompanyCoupons(int companyId) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM COUPONS WHERE company_id= '%d'", companyId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Company coupons were deleted");

	}

	/**
	 * getOneCoupon() a method that gets a single coupon info from DB by coupon ID
	 * 
	 * @param couponID a coupon ID to get the info of
	 * @return a Coupon object from DB with all the coupon data
	 * @throws Exception can throws Exception
	 */
	public Coupon getOneCoupon(int couponID) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE coupon_id=%d", couponID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int couponId = resultSet.getInt("coupon_id");
					int companyId = resultSet.getInt("company_id");
					int categoryId = resultSet.getInt("category_id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					Date startDate = resultSet.getDate("start_date");
					Date endDate = resultSet.getDate("end_date");
					int amount = resultSet.getInt("amount");
					double price = resultSet.getDouble("price");
					String image = resultSet.getString("image");

					Coupon coupon = new Coupon(couponId, companyId, categoryIdToString(categoryId), title, description,
							startDate, endDate, amount, price, image);

					return coupon;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * getAllCoupons() a method that gets all the coupons and their info from the DB
	 * 
	 * @return an ArrayList of all the coupons and their data in the DB
	 * @throws Exception can throws Exception
	 */
	public ArrayList<Coupon> getAllCoupons() throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = ("SELECT * FROM COUPONS");

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> couponList = new ArrayList<Coupon>();

					while (resultSet.next()) {

						int couponId = resultSet.getInt("coupon_id");
						int companyId = resultSet.getInt("company_id");
						int categoryId = resultSet.getInt("category_id");
						String title = resultSet.getString("title");
						String description = resultSet.getString("description");
						Date startDate = resultSet.getDate("start_date");
						Date endDate = resultSet.getDate("end_date");
						int amount = resultSet.getInt("amount");
						double price = resultSet.getDouble("price");
						String image = resultSet.getString("image");

						Coupon coupon = new Coupon(couponId, companyId, categoryIdToString(categoryId), title,
								description, startDate, endDate, amount, price, image);

						couponList.add(coupon);
					}

					return couponList;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * updateCouponAmount() a method that updates a specific coupon amount. -1 if
	 * purchased and 1 if purchase is canceled.
	 * 
	 * @param couponId           the coupon ID that needed to update its amount
	 * @param couponManipulation the manipulation on the coupon amount that is
	 *                           needed -1 if purchased and 1 if purchase is
	 *                           canceled.
	 * @throws Exception can throws Exception
	 */
	public void updateCouponAmount(int couponId, int couponManipulation) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE coupon_id=%d", couponId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int newCouponAmount = ((resultSet.getInt("amount")) + couponManipulation);

					Coupon updatedCoupon = getOneCoupon(couponId);
					updatedCoupon.setAmount(newCouponAmount);
					updateCoupon(updatedCoupon);

				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * addCouponPurchase() a method that adds a purchase of a coupon by a customer
	 * in the coustomers_vs_coupons table in the DB
	 * 
	 * @param customerId the customer that purchased ID
	 * @param couponId   the coupon purchased ID
	 * @throws Exception can throws Exception
	 */
	public void addCouponPurchase(int couponId, int customerId) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO CUSTOMERS_VS_COUPONS(coupon_id, customer_id) VALUES('%d', '%d')",
					couponId, customerId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}
		System.out.println("New coupon purchase added successfully");
	}

	/**
	 * deleteCouponPurchase() a method that deletes a specific coupon purchase made
	 * by a specific customer from the coustomers_vs_coupons table in the DB
	 * 
	 * @param customerId the customer that purchased ID
	 * @param couponId   the coupon purchased ID
	 * @throws Exception can throws Exception
	 */
	public void deleteCouponPurchase(int couponId, int customerId) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE coupon_id= '%d' AND customer_id= '%d'",
					couponId, customerId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Coupon was deleted");

	}

	/**
	 * deleteAllCouponPurchaseByCouponId() a method that deletes all the coupon
	 * purchases made of a specific coupon from the coustomers_vs_coupons table in
	 * the DB
	 * 
	 * @param couponId the coupon ID needs to delete
	 * @throws Exception can throws Exception
	 */
	public void deleteAllCouponPurchaseByCouponId(int couponId) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE coupon_id=%d", couponId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Coupon purchase history of this coupon was deleted");

	}

	/**
	 * deleteAllCouponPurchaseByCustomerId() a method that deletes all the coupon
	 * purchases made by a specific customer from the coustomers_vs_coupons table in
	 * the DB
	 * 
	 * @param customerId the customer ID needs to delete
	 * @throws Exception can throws Exception
	 */
	public void deleteAllCouponPurchaseByCustomerId(int customerId) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE customer_id=%d", customerId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();

			}

		} finally {
			connectionPool.restoreConnection(connection);
		}

		System.out.println("Coupon purchase history of customer was deleted");
	}

	/**
	 * deleteAllCouponPurchaseByCompanyId() a method that deletes all the coupon
	 * purchases of coupons related to a specific company from the
	 * coustomers_vs_coupons table in the DB
	 * 
	 * @param companyId the company ID needs to delete
	 * @throws Exception can throws Exception
	 */
	public void deleteAllCouponPurchaseByCompanyId(int companyId) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			int couponId = 0;
			String sql1 = String.format("SELECT * FROM COUPONS WHERE company_id=%d", companyId);

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {

				try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {

					while (resultSet1.next()) {

						couponId = resultSet1.getInt("Coupon_id");
						String sql2 = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE coupon_id=%d", couponId);

						try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql2)) {
							preparedStatement2.executeUpdate();
						}
					}
					System.out.println("Deleted company purchase history Successfully");
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	/**
	 * deleteAllExpiredCoupons() a method for the use of the daily job that deletes
	 * all the coupon that expired and from the purchases history
	 * 
	 * @throws Exception can throws Exception
	 */
	public void deleteAllExpiredCoupons() throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT coupon_id FROM COUPONS");

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {

						int couponId = resultSet.getInt("Coupon_id");

						if (isCouponExpiered(couponId)) {

							System.out.println("Coupon number " + couponId + " has expired");
							deleteAllCouponPurchaseByCouponId(couponId);
							deleteCoupon(couponId);
						}
					}
				}

			}
		} finally {
			connectionPool.restoreConnection(connection);

		}
	}

}

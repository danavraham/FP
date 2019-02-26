package dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dao.ICouponsDAO;
import javaBeans.Category;
import javaBeans.Coupon;

public class CouponsDBDAO implements ICouponsDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();

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

	public static int categoryStringToId(Category category) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM  CATEGORIES WHERE category_name='%s' ",
					category.valueOf(category.toString()));

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

	@Override
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

	@Override
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

	@Override
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

	@Override
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
						double price = resultSet.getInt("price");
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

	@Override
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
					double price = resultSet.getInt("price");
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

	@Override
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

	@Override
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

	
	@Override
	public boolean isCouponExistById(int couponId) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM COUPONS WHERE coupon_id = '%d'",
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
	
//	do I use it in the purchase?
	@Override
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

	@Override
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

	@Override
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

	@Override
	public void deleteAllCouponPurchaseByCompanyId(int companyId) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();
			
			int couponId = 0;
			String sql1 = String.format("SELECT * FROM COUPONS WHERE company_id=%d", companyId);
			String sql2 = String.format("DELETE FROM CUSTOMERS_VS_COUPONS WHERE coupon_id=%d", couponId);
													

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {

				try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {

					while (resultSet1.next()) {

						couponId = resultSet1.getInt("Coupon_id");

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

	@Override
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

	@Override
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

	@Override
	public void updateCouponAmount(int couponId, int couponManipulation) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COUPONS WHERE coupon_id=%d", couponId);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int newCouponAmount = ((resultSet.getInt("amount"))+couponManipulation);

					Coupon updatedCoupon = getOneCoupon(couponId);
					updatedCoupon.setAmount(newCouponAmount);
					updateCoupon(updatedCoupon);

				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	
}

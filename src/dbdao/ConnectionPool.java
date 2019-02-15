package dbdao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

	// --> Setting the connections attributes
	private static String USERNAME = "root";
	private static String PASSWORD = null;
	private static String URL = "jdbc:mysql://localhost:3306/coupon_system";

	// --> Creating a SingleTon
	private static ConnectionPool instance = new ConnectionPool(); 

	// --> Creating a Stack of optional connections
	private Stack<Connection> connections = new Stack<>(); 


	// --> Stacking connection options to the stack
	private ConnectionPool() {
		for (int i = 1; i <= 10; i++) {
			try {
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				connections.push(conn); // --> pushes to the stack
			} catch (SQLException e) {
			}
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}

	public void restoreConnection(Connection conn) {

		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	public void closeAllConnection() throws InterruptedException {

		synchronized (connections) {

			while (connections.size() < 10) {
				connections.wait();
			}

			for (Connection conn : connections) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}
}


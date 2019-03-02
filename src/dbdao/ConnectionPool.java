package dbdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * ConnectionPool class creates a singleTon to allow basic connection to the DB
 * for all the DBDAO classes
 *
 * @author dan
 *
 */
public class ConnectionPool {

	// Setting the connections attributes
	private static String USERNAME = "root";
	private static String PASSWORD = null;
	private static String URL = "jdbc:mysql://localhost:3306/coupon_system";

	// Creating a SingleTon
	private static ConnectionPool instance = new ConnectionPool();

	// Creating a Stack of optional connections
	private Stack<Connection> connections = new Stack<>();

	/**
	 * A constructor that creates a stacking of 10 connections
	 */
	private ConnectionPool() {
		for (int i = 1; i <= 10; i++) {
			try {
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				connections.push(conn); // pushes to the stack
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * getInstance() returns an instance for the connection
	 * 
	 * @return returns the instance for this connection pool
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * getConnection() returns the first available connection from the Stack
	 * 
	 * @return the first available connection from the Stack
	 * @throws InterruptedException Can throw an InterruptedException
	 */
	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}

	/**
	 * restoreConnection() gets the connection after used and returns it to the
	 * Stack of connections
	 * 
	 * @param conn the connection to return to the Stack
	 */
	public void restoreConnection(Connection conn) {

		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	/**
	 * closeAllConnection() closes all the connections
	 * 
	 * @throws InterruptedException Can throw an InterruptedException
	 */
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

package dao;

import java.sql.*;

public class DbConnect {
	private static Connection connection = null;
	static {
		try {
			String dbdriver = "com.mysql.cj.jdbc.Driver";
			String dbpath = "jdbc:mysql://localhost:3306/splitlee_db?useSSL=false&allowPublicKeyRetrieval=true";
			String dbid = "root";
			String dbpass = "Umang@123";
			Class.forName(dbdriver);
			connection = DriverManager.getConnection(dbpath, dbid, dbpass);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * returns the connection
	 * @return
	 */
	public static Connection getConnection() {
		return connection;
	}
}

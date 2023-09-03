package com.fssa.pupdesk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public Connection getConnection() {

		String dbUrl;
		String dbUser;
		String dbPassword;
		dbUrl = System.getenv("DB_URL");
		dbUser = System.getenv("DB_USER");
		dbPassword = System.getenv("DB_PASSWORD");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to Connect database", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Driver Not Found");
		}

	}
}

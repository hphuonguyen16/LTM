package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class PointDAO {

	public static Connection getConnection() {
		String dbURL = "jdbc:mysql://localhost:3306/ELW";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, "root", "");
			System.out.println("Database connected successfully!");
		} catch (Exception ex) {
			System.out.println("Connecting database failed!");
			ex.printStackTrace();
		}
		return conn;
	}
}

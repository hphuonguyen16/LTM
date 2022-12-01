package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Point;

public class PointDAO {
	public ArrayList<Point> getAllPointsByUserID(int userID) {
		ArrayList<Point> result = new ArrayList<Point>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM point");
			while (rs.next()) {
				Point point = new Point();
				point.setPointID(rs.getInt(1));
				point.setPoints(rs.getInt(2));
				point.setUserID(rs.getInt(3));
				point.setLessonID(rs.getInt(4));
				result.add(point);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public int addNewPoint(int userID, int lessonID, int points) {
		int status = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO point (points, userID, lessonID) values (?,?,?)");
			stmt.setInt(1, points);
			stmt.setInt(2, userID);
			stmt.setInt(3, lessonID);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

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

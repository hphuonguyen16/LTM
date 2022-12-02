package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Point_Lesson;

public class PointDAO {
	public ArrayList<Point_Lesson> getPointAndLessonByUserID(int userID) {
		ArrayList<Point_Lesson> result = new ArrayList<Point_Lesson>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT l.topic, l.level, p.points, Ctr.questionCount FROM point p LEFT OUTER JOIN lesson l ON p.lessonID = l.lessonID "
							+ "LEFT OUTER JOIN (SELECT q.lessonID, COUNT(*) as questionCount from question q GROUP BY q.lessonID) Ctr "
							+ "ON l.lessonID = Ctr.lessonID WHERE p.userID = " + userID);
			while (rs.next()) {
				Point_Lesson point_lesson = new Point_Lesson();
				point_lesson.setTopic(rs.getString("topic"));
				point_lesson.setLevel(rs.getInt("level"));
				point_lesson.setPoints(rs.getInt("points"));
				point_lesson.setNum_question(rs.getInt("questionCount"));
				result.add(point_lesson);
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

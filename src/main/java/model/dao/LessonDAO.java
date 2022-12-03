package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Lesson;

public class LessonDAO {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/elw";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private static final String SELECT_ALL_LESSONS = "select * from lesson";

	public LessonDAO() {
		// TODO Auto-generated constructor stub
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Lesson> getAllLessons() {
		List<Lesson> lessons = new ArrayList<>();
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_LESSONS);
			while (rs.next()) {
				int id = rs.getInt(1);
				String topic = rs.getString("topic");
				int level = rs.getInt("level");
				lessons.add(new Lesson(id, topic, level));
			}
		} catch (SQLException e) {
//			printSQLException(e);
		}
		return lessons;
	}

	public void AddNewLesson(String _topic, String _level) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elw", "root", "");
			String sql = "insert into lesson(topic,level) values( ?, ?)";
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1, _topic);
			statement.setString(2, _level);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}

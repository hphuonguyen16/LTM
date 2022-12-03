package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.*;

public class LessonDAO {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/data";
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

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Lesson> lessons = new ArrayList<>();
		// Step 1: Establishing a Connection
		try {
			Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LESSONS);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String topic = rs.getString("topic");
				int level  = rs.getInt("level");
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

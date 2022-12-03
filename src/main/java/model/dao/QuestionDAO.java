package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Choices;
import model.bean.Question;

public class QuestionDAO {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/elw";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private static final String SELECT_ALL_QUESTIONS_BY_LESSON_ID = "select * from question where lessonID = ?;";
	private static final String SELECT_ALL_CHOICES_BY_QUESTION_ID = "select * from choices where question_id = ?;";
	private static final String INSERT_QUESTION = "INSERT INTO question" + "  (question, lessonID) VALUES "
			+ " (?, ?);";
	private static final String INSERT_CHOICES = "INSERT INTO choices" + "  (answer, correct, question_id) VALUES "
			+ " (?, ?, ?);";
	private static final String DELETE_QUESTION = "delete from question where questionID = ?;";
	private static final String DELETE_CHOICE = "delete from choices where question_id = ?;";

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

	public QuestionDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<Question> getAllQuestionsByLessonId(int lessonId) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Question> questions = new ArrayList<>();
		// Step 1: Establishing a Connection
		try {
			Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUESTIONS_BY_LESSON_ID);
			preparedStatement.setInt(1, lessonId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt(1);
				String question = rs.getString("question");
				int lesson_id = rs.getInt("lessonID");
				questions.add(new Question(id, question, lesson_id));
			}
			System.out.println(questions);
		} catch (SQLException e) {
//			printSQLException(e);
		}
		return questions;
	}

	public Question getQuestionsByIndex(int index, int lesson_id) {
		List<Question> questions = new ArrayList<>();
		questions = this.getAllQuestionsByLessonId(lesson_id);
		Question ques = questions.get(index);
		return ques;
	}

	public List<Choices> getChoicesByQuestionId(int question_id) throws ClassNotFoundException {
		List<Choices> choices = new ArrayList<>();
		try {
//			Connection connection = getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://127.0.0.1:3306/elw";
			Connection connection = DriverManager.getConnection(connectionURL, "root", "");
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CHOICES_BY_QUESTION_ID);
			preparedStatement.setInt(1, question_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt(1);
				String answer = rs.getString("answer");
				Boolean correct = rs.getBoolean("correct");
				choices.add(new Choices(id, answer, correct, question_id));
			}
		} catch (SQLException e) {
//			printSQLException(e);
		}
		return choices;
	}

	public int insertQuestion(Question question) throws SQLException {
		System.out.println(INSERT_QUESTION);
		// try-with-resource statement will auto close the connection.
		ResultSet rs = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, question.getQuestion());
			preparedStatement.setInt(2, question.getLessonID());
			preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
			int autoIncKeyFromApi = -1;

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			}
			System.out.println("Key returned from getGeneratedKeys()" + autoIncKeyFromApi);
			return autoIncKeyFromApi;
		} catch (SQLException e) {
		}
		return 0;
	}

	public boolean deleteQuestion(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUESTION);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public void insertChoice(Choices choice) throws SQLException {
		System.out.println(INSERT_CHOICES);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHOICES)) {
			preparedStatement.setString(1, choice.getAnswer());
			preparedStatement.setBoolean(2, choice.isCorrect());
			preparedStatement.setInt(3, choice.getQuestionID());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public boolean deleteChoice(int questionID) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CHOICE);) {
			statement.setInt(1, questionID);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

}

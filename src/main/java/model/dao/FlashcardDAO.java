package model.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import model.bean.Flashcard;

public class FlashcardDAO {
	public ArrayList<Flashcard> getAllFlashcards(int userID) {
		ArrayList<Flashcard> result = new ArrayList<Flashcard>();
		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM flashcard WHERE userID = " + userID);
			while (rs.next()) {
				Flashcard flashcard = new Flashcard();
				flashcard.setFlashcardID(rs.getInt(1));
				flashcard.setWord(rs.getString(2));
				flashcard.setWord_type(rs.getString(3));
				flashcard.setMeaning(rs.getString(4));
				flashcard.setImage(retrieveBase64Image(rs.getBlob(5)));
				if (rs.getInt(6) == 0)
					flashcard.setUserID(rs.getInt(6));
				result.add(flashcard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<Flashcard> getRandomizedFlashcards(int userID, int... number) {
		int card_number = (number.length > 0) ? number[0] : 20;
		ArrayList<Flashcard> result = new ArrayList<Flashcard>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM flashcard WHERE ( userID = 0 OR userID = " + userID
					+ "	) ORDER BY RAND() LIMIT " + card_number);
			while (rs.next()) {
				Flashcard flashcard = new Flashcard();
				flashcard.setFlashcardID(rs.getInt(1));
				flashcard.setWord(rs.getString(2));
				flashcard.setWord_type(rs.getString(3));
				flashcard.setMeaning(rs.getString(4));
				flashcard.setImage(retrieveBase64Image(rs.getBlob(5)));
				if (rs.getInt(6) == 0)
					flashcard.setUserID(rs.getInt(6));
				result.add(flashcard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public Flashcard getFlashcardDetail(int flashcardID) {
		Flashcard result = null;
		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM flashcard WHERE flashcardID = " + flashcardID);
			while (rs.next()) {
				Flashcard flashcard = new Flashcard();
				flashcard.setFlashcardID(rs.getInt(1));
				flashcard.setWord(rs.getString(2));
				flashcard.setWord_type(rs.getString(3));
				flashcard.setMeaning(rs.getString(4));
				flashcard.setImage(retrieveBase64Image(rs.getBlob(5)));
				if (rs.getInt(6) == 0)
					flashcard.setUserID(rs.getInt(6));
				return flashcard;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public int addNewFlashcard(String word, String word_type, String meaning, InputStream image, int userID) {
		int status = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO flashcard (word, word_type, meaning, image, userID) values (?,?,?,?,?)");
			stmt.setString(1, word);
			stmt.setString(2, word_type);
			stmt.setString(3, meaning);
			stmt.setBlob(4, image);
			stmt.setInt(5, userID);

			status = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	public int updateFlashcard(int flashcardID, String word, String word_type, String meaning, InputStream image) {
		int status = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"UPDATE flashcard SET word = ?, word_type = ?, meaning = ?, image = ? WHERE flashcardID = "
							+ flashcardID);
			stmt.setString(1, word);
			stmt.setString(2, word_type);
			stmt.setString(3, meaning);
			stmt.setBlob(4, image);

			status = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	public int deleteFlashcard(int flashcardID) {
		int status = 0;
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			status = stmt.executeUpdate("DELETE FROM flashcard WHERE flashcardID = " + flashcardID);
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

	public static String retrieveBase64Image(Blob image) throws SQLException {
		byte[] imageBytes = null;
		try {
			InputStream inputStream = image.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			imageBytes = outputStream.toByteArray();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return Base64.getEncoder().encodeToString(imageBytes);
	}
}

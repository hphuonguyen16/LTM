package model.bo;

import java.io.InputStream;
import java.util.ArrayList;

import model.bean.Flashcard;
import model.dao.FlashcardDAO;

public class FlashcardBO {

	FlashcardDAO flashcardDAO = new FlashcardDAO();

	public FlashcardBO() {
		// TODO Auto-generated constructor stub
	}

	public Flashcard showFlashcardDetail(int flashcardID) {
		return flashcardDAO.getFlashcardDetail(flashcardID);
	}

	public int addNewFlashcard(String word, String word_type, String meaning, InputStream image, int userID) {
		return flashcardDAO.addNewFlashcard(word, word_type, meaning, image, userID);
	}

	public int updateFlashcard(int flashcardID, String word, String word_type, String meaning, InputStream image) {
		return flashcardDAO.updateFlashcard(flashcardID, word, word_type, meaning, image);
	}

	public int deleteFlashcard(int flashcardID) {
		return flashcardDAO.deleteFlashcard(flashcardID);
	}

	public ArrayList<Flashcard> getAllFlashcards(int userID) {
		return flashcardDAO.getAllFlashcards(userID);
	}

	public ArrayList<Flashcard> getRandomizedFlashcards(int userID) {
		return flashcardDAO.getRandomizedFlashcards(userID);
	}
}

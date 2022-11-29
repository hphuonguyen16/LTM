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

	public int addNewFlashcard(String word, String word_type, String meaning, InputStream image, int userID) {
		return flashcardDAO.addNewFlashcard(word, word_type, meaning, image, userID);
	}

	public ArrayList<Flashcard> getAllFlashcards() {
		return flashcardDAO.getAllFlashcards(0);
	}

	public ArrayList<Flashcard> getRandomizedFlashcards(int userID) {
		return flashcardDAO.getRandomizedFlashcards(userID);
	}
}

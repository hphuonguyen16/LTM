package model.bean;

public class Flashcard {
	private int flashcardID;
	private String word;
	private String word_type;
	private String meaning;
	private String image;
	private Integer userID;

	public Flashcard() {
		// TODO Auto-generated constructor stub
	}

	public int getFlashcardID() {
		return flashcardID;
	}

	public void setFlashcardID(int flashcardID) {
		this.flashcardID = flashcardID;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord_type() {
		return word_type;
	}

	public void setWord_type(String word_type) {
		this.word_type = word_type;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}
}

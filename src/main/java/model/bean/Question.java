package model.bean;

public class Question {
	private int quizID;
	private String question;
	private int lessonID;
	public Question() {
		// TODO Auto-generated constructor stub
	}
	public int getQuizID() {
		return quizID;
	}
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getLessonID() {
		return lessonID;
	}
	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
	}
	

}

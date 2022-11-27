package model.bean;

public class Choices {
	private int choiceID;
	private String answer;
	private boolean correct;
	private int questionID;
	public Choices() {
		// TODO Auto-generated constructor stub
	}
	public int getChoiceID() {
		return choiceID;
	}
	public void setChoiceID(int choiceID) {
		this.choiceID = choiceID;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	

}

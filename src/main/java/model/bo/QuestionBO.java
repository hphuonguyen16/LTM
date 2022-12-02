package model.bo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.*;
import model.bean.Choices;
import model.bean.Question;
import model.dao.QuestionDAO;
public class QuestionBO {
	
	private QuestionDAO questionDAO;
	public QuestionBO() {
		// TODO Auto-generated constructor stub
		questionDAO = new QuestionDAO();
	}
	public List<Question> getAllQuestionsByLessonId(int lessonId) {
		return questionDAO.getAllQuestionsByLessonId(lessonId);
	}
	
	public Question getQuestionsByIndex(int index, int lesson_id) {
		return questionDAO.getQuestionsByIndex(index, lesson_id);
	}
	
	
	public  List<Choices> getAllChoices(List<Question> questions) throws ClassNotFoundException{
		List<Choices> choices  = new ArrayList<>();
		for(int i=0; i<questions.size();i++) {
			List<Choices> choices_temp =  questionDAO.getChoicesByQuestionId(questions.get(i).getQuizID());
			for (int j=0; j<choices_temp.size();j++) {
				choices.add(choices_temp.get(j));
			}
		}
		for(int i=0; i<choices.size();i++) {
			System.out.println(choices.get(i).getAnswer());
		}
		return choices;
	}
	
	public HashMap<Question,List<Choices>> getAllQuestions_Choices(int lesson_id) throws ClassNotFoundException{
		HashMap<Question,List<Choices>> map = new HashMap<Question,List<Choices>>();
		List<Question> questions = new ArrayList<Question>();
		questions = questionDAO.getAllQuestionsByLessonId(lesson_id);
		for (int i=0; i<questions.size(); i++) {
			List<Choices> choices_temp =  questionDAO.getChoicesByQuestionId(questions.get(i).getQuizID());
			map.put(questions.get(i), choices_temp);
		}
		return map;
	}

	
	public int insertQuestion(Question question) throws SQLException {
		return questionDAO.insertQuestion(question);
	}
	
	public boolean deleteQuestion(int id) throws SQLException {
		return questionDAO.deleteQuestion(id);
		
	}
	
	public void insertChoice(Choices choice) throws SQLException {
		questionDAO.insertChoice(choice);
	}
	
	public boolean deleteChoice(int questionID) throws SQLException {
		return questionDAO.deleteChoice(questionID);
	}

	
	
	
}

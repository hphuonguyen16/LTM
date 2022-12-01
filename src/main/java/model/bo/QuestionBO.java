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
			System.out.println(questions.get(i).getQuestion());
		}
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
}

package model.bo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	
	public List<Choices> getChoicesByQuestionId(int question_id) throws ClassNotFoundException{
		return questionDAO.getChoicesByQuestionId(question_id);
	}
	
	public Choices getCorrectChoice(int question_id) throws ClassNotFoundException {
		List<Choices> choices = this.getChoicesByQuestionId(question_id);
		for(int i=0; i<choices.size(); i++) {
			if(choices.get(i).isCorrect())
			{
				System.out.print(choices.get(i).getAnswer());
				return choices.get(i);
			}
		}
		return null;
	}

}

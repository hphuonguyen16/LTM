package controller;

import model.bean.*;
import model.bo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionBO questionBO = new QuestionBO();
    
    public QuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action_parameter = (String) request.getParameter("action");
		System.out.print(action_parameter);
		try {
			switch (action_parameter) {
			case "getQuestion":
				//showQuestions(request, response);
				try {
					showQuestions(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "checkAnswer":
				checkAnswer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private void showQuestions(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
    	int next_ques = Integer.parseInt(request.getParameter("next_ques"))+1;
    	System.out.print(Integer.parseInt(request.getParameter("next_ques")));
    	int num_ques= Integer.parseInt(request.getParameter("next_ques"))+1 ;
    	List<Question> questions = questionBO.getAllQuestionsByLessonId(1);
    	if(next_ques>=questions.size()) {
    		next_ques = questions.size();
    		num_ques = questions.size();
    	}	
    	else if(next_ques==0||next_ques==-1) {
    		next_ques = 1;
    		num_ques = 1;
    	}
    	System.out.print(next_ques);
    	Question question = questionBO.getQuestionsByIndex(next_ques-1, lesson_id);
    	List<Choices> choices = questionBO.getChoicesByQuestionId(question.getQuizID());
		request.setAttribute("question", question);
		request.setAttribute("choices", choices);
		request.setAttribute("questions", questions);
		request.setAttribute("next_ques", next_ques);
		request.setAttribute("num_ques", num_ques);
		RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
		dispatcher.forward(request, response);
	}
    
    private void checkAnswer(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int question_id = Integer.parseInt(request.getParameter("question_id"));
    	Choices correctChoice = questionBO.getCorrectChoice(question_id);
    	request.setAttribute("correctChoice", correctChoice);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
		dispatcher.forward(request, response);
	}
 
}

package controller;

import model.bean.*;
import model.bo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizController
 * @param <RequestDispatcher>
 * @param <HttpServletRequest>
 * @param <HttpServletResponse>
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
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} 
	}
    private void showQuestions(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
    	List<Question> questions = questionBO.getAllQuestionsByLessonId(lesson_id);
    	List<Choices> choices = questionBO.getAllChoices(questions);
    	request.setAttribute("questions", questions);
    	request.setAttribute("choices", choices);
		RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
		dispatcher.forward(request, response);
	}
}

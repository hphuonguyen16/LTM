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
		if (action_parameter.equals("showAddForm")) {
			try {
				LessonBO lessonBO = new LessonBO();
				List<Lesson> lessons = lessonBO.getAllLessons();
		    	request.setAttribute("lessons", lessons);
				RequestDispatcher dispatcher = request.getRequestDispatcher("addQuestion_admin.jsp");
				dispatcher.forward(request, response);
			} catch (IOException | ServletException e) {}
			}
		else if (action_parameter.equals("addQuestion") ) {
			try {
				addQuestion(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (action_parameter.equals("getQuestion") ) {
		try {
			showQuestions(request, response);
		} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if (action_parameter.equals("showAllQuestions_admin") ) {
			try {
				showAllQuestions_admin(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		else if (action_parameter.equals("deleteQuestion_admin") ) {
			try {
				deleteQuestion_admin(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    private void showAllQuestions_admin(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
    	HashMap<Question, List<Choices>> allQuizes = questionBO.getAllQuestions_Choices(lesson_id);
    	System.out.print(allQuizes);
    	request.setAttribute("allQuizes", allQuizes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("questions_admin.jsp");
		dispatcher.forward(request, response);
	}
    private void deleteQuestion_admin(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int question_id = Integer.parseInt(request.getParameter("question_id"));
    	int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
    	questionBO.deleteQuestion(question_id);
    	response.sendRedirect("QuizServlet?action=showAllQuestions_admin&lesson_id="+lesson_id);
	}
    private void addQuestion(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
    	int lesson_id = Integer.parseInt(request.getParameter("lesson"));
    	String question = (String)(request.getParameter("question"));
    	int question_id = questionBO.insertQuestion(new Question(1, question, lesson_id));
    	System.out.print(question_id);
    	String answer1 = (String)(request.getParameter("answer1"));
    	String answer2 = (String)(request.getParameter("answer2"));
    	String answer3 = (String)(request.getParameter("answer3"));
    	String answer4 = (String)(request.getParameter("answer4"));
    	
    	String value= request.getParameter("answer");
    	System.out.println(value);
    	if(value.equals("1")) {
    		questionBO.insertChoice(new Choices(1,answer1,true, question_id));
    		questionBO.insertChoice(new Choices(1,answer2,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer3,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer4,false, question_id));
    	}
    	else if(value.equals("2")) {
    		questionBO.insertChoice(new Choices(1,answer1,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer2,true, question_id));
    		questionBO.insertChoice(new Choices(1,answer3,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer4,false, question_id));
    	}
    	else if(value.equals("3")) {
    		questionBO.insertChoice(new Choices(1,answer1,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer2,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer3,true, question_id));
    		questionBO.insertChoice(new Choices(1,answer4,false, question_id));
    	}
    	else if(value.equals("4")) {
    		questionBO.insertChoice(new Choices(1,answer1,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer2,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer3,false, question_id));
    		questionBO.insertChoice(new Choices(1,answer4,true, question_id));
    	}
    	response.sendRedirect("QuizServlet?action=showAllQuestions_admin&lesson_id="+lesson_id);
	}	
}

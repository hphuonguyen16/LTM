package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Choices;
import model.bean.Lesson;
import model.bean.Question;
import model.bo.LessonBO;
import model.bo.QuestionBO;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionBO questionBO = new QuestionBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action_parameter = (String) request.getParameter("action");
		if (action_parameter == null) {
			try {
				LessonBO lessonBO = new LessonBO();
				List<Lesson> lessons = lessonBO.getAllLessons();
				request.setAttribute("lessons", lessons);
				RequestDispatcher dispatcher = request.getRequestDispatcher("addQuestion.jsp");
				dispatcher.forward(request, response);
			} catch (IOException | ServletException e) {
			}
		} else if (action_parameter.equals("addQuestion")) {
			try {
				addQuestion(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void addQuestion(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		int lesson_id = Integer.parseInt(request.getParameter("lesson"));
		String question = (String) (request.getParameter("question"));
		int question_id = questionBO.insertQuestion(new Question(1, question, lesson_id));
		System.out.print(question_id);
		String answer1 = (String) (request.getParameter("answer1"));
		String answer2 = (String) (request.getParameter("answer2"));
		String answer3 = (String) (request.getParameter("answer3"));
		String answer4 = (String) (request.getParameter("answer4"));

		String value = request.getParameter("answer");
		System.out.println(value);
		if (value.equals("1")) {
			questionBO.insertChoice(new Choices(1, answer1, true, question_id));
			questionBO.insertChoice(new Choices(1, answer2, false, question_id));
			questionBO.insertChoice(new Choices(1, answer3, false, question_id));
			questionBO.insertChoice(new Choices(1, answer4, false, question_id));
		} else if (value.equals("2")) {
			questionBO.insertChoice(new Choices(1, answer1, false, question_id));
			questionBO.insertChoice(new Choices(1, answer2, true, question_id));
			questionBO.insertChoice(new Choices(1, answer3, false, question_id));
			questionBO.insertChoice(new Choices(1, answer4, false, question_id));
		} else if (value.equals("3")) {
			questionBO.insertChoice(new Choices(1, answer1, false, question_id));
			questionBO.insertChoice(new Choices(1, answer2, false, question_id));
			questionBO.insertChoice(new Choices(1, answer3, true, question_id));
			questionBO.insertChoice(new Choices(1, answer4, false, question_id));
		} else if (value.equals("4")) {
			questionBO.insertChoice(new Choices(1, answer1, false, question_id));
			questionBO.insertChoice(new Choices(1, answer2, false, question_id));
			questionBO.insertChoice(new Choices(1, answer3, false, question_id));
			questionBO.insertChoice(new Choices(1, answer4, true, question_id));
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);

	}

	private void showQuestions(HttpServletRequest request, HttpServletResponse response) {

	}

}

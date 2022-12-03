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

import model.bean.*;
import model.bo.LessonBO;

/**
 * Servlet implementation class LessonServlet
 */
@WebServlet("/LessonServlet")
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LessonBO lessonBO = new LessonBO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		switch (action) {
		case "AddNewLesson": {
			
			try {
				AddNewLesson(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
			
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.print(action);
		switch (action ){
		case "/LessonServlet":
		{
			try {
				showLessons(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
	}
	private void showLessons(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Lesson> lessons = lessonBO.getAllLessons();
		request.setAttribute("lessons", lessons);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lesson.jsp");
		dispatcher.forward(request, response);
	}
	private void AddNewLesson(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException, ClassNotFoundException {		
		String destination = null;
		String topic = request.getParameter("topic");
		String level = request.getParameter("level");
		LessonBO lessonBo = new LessonBO();
		lessonBo.AddNewLesson(topic,level);;
		destination ="/login.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
			
	}

}

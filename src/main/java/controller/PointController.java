package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Point_Lesson;
import model.bo.PointBO;

/**
 * Servlet implementation class FlashcardController
 */
@WebServlet("/point")
@MultipartConfig(maxFileSize = 25 * 1024 * 1024)
public class PointController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PointController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "getAllPointsByUserID":
			getAllPointsByUserID(request, response);
			break;
		case "addNewPoint":
			insertPoint(request, response);
			break;
		case "delete":
//			deleteBook(request, response);
			break;
		case "update":
//			updateBook(request, response);
			break;
		default:
//			getAllFlashcard(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getAllPointsByUserID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		PointBO pointBO = new PointBO();
		ArrayList<Point_Lesson> points = pointBO.getPointAndLessonByUserID(userID);
//		System.out.println(points);
		request.setAttribute("listPoint", points);
		String destination = "/point.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

	private void insertPoint(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		int userID = Integer.parseInt(request.getParameter("userID"));
		int lessonID = Integer.parseInt(request.getParameter("lessonID"));
		int points = Integer.parseInt(request.getParameter("lessonID"));
		if (userID != 0 && lessonID != 0) {
			PointBO pointBO = new PointBO();
			int status = pointBO.addNewPoint(userID, lessonID, points);
			if (status > 0) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				System.out.println("flashcard added successfully");
			} else {
				System.out.println("flashcard adding failed");
			}
		}
	}
}

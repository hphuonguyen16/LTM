package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Flashcard;
import model.bo.FlashcardBO;

/**
 * Servlet implementation class FlashcardController
 */
@WebServlet("/flashcard")
@MultipartConfig(maxFileSize = 25 * 1024 * 1024)
public class FlashcardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static PrintWriter out;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlashcardController() {
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
		case "getRandomizedFlashcards":
			getRandomizedFlashcard(request, response);
			break;
		case "addNewFlashcard":
			insertFlashcard(request, response);
			break;
		case "delete":
//			deleteBook(request, response);
			break;
		case "update":
//			updateBook(request, response);
			break;
		default:
			getAllFlashcard(request, response);
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

	private void insertFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		String word = request.getParameter("word");
		String word_type = request.getParameter("word_type");
		String meaning = request.getParameter("meaning");
		Part part = request.getPart("image");
		int userID = request.getParameter("userID") != null ? Integer.parseInt(request.getParameter("userID")) : 0;
		if (word != null && word_type != null && meaning != null && part != null) {
			FlashcardBO flashcardBO = new FlashcardBO();
			int status = flashcardBO.addNewFlashcard(word, word_type, meaning, part.getInputStream(), userID);
			if (status > 0) {
				System.out.println("flashcard added successfully");
			} else {
				System.out.println("flashcard adding failed");
			}
		}
	}

	private void getAllFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FlashcardBO flashcardBO = new FlashcardBO();
		ArrayList<Flashcard> flashcards = flashcardBO.getAllFlashcards();
		System.out.println(flashcards);
		request.setAttribute("listFlashcard", flashcards);
		String destination = "/flashcard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

	private void getRandomizedFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userID = request.getParameter("userID") != null ? Integer.parseInt(request.getParameter("userID")) : 0;
		FlashcardBO flashcardBO = new FlashcardBO();
		ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
		request.setAttribute("listFlashcard", flashcards);
		String destination = "/flashcard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}

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
		switch (action) {
		case "getRandomizedFlashcards":
			getRandomizedFlashcard(request, response);
			break;
		case "addNewFlashcard":
			insertFlashcard(request, response);
			break;
		case "showCard":
			showFlashcardToUpdate(request, response);
			break;
		case "deleteFlashcard":
			deleteFlashcard(request, response);
			break;
		case "updateFlashcard":
			updateFlashcard(request, response);
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
				ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
				ArrayList<Flashcard> myCards = flashcardBO.getAllFlashcards(userID);
				request.setAttribute("listFlashcard", flashcards);
				request.setAttribute("myCards", myCards);
				String destination = "/flashcard.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
				dispatcher.forward(request, response);
				System.out.println("flashcard added successfully");
			} else {
				System.out.println("flashcard adding failed");
			}
		}
	}

	private void showFlashcardToUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		int flashcardID = Integer.parseInt(request.getParameter("flashcardID"));
		FlashcardBO flashcardBO = new FlashcardBO();
		Flashcard flashcard = flashcardBO.showFlashcardDetail(flashcardID);
		if (flashcard != null) {
			request.setAttribute("flashcard", flashcard);
			String destination = "/customFlashcard.jsp?action=update";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
			dispatcher.forward(request, response);
			System.out.println("flashcard shown successfully");
		} else {
			System.out.println("getting flashcard detail failed");
		}
	}

	private void updateFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		int flashcardID = Integer.parseInt(request.getParameter("flashcardID"));
		String word = request.getParameter("word");
		String word_type = request.getParameter("word_type");
		String meaning = request.getParameter("meaning");
		Part part = request.getPart("image");
		System.out.println(part.getInputStream());
		if (word != null && word_type != null && meaning != null && part != null) {
			FlashcardBO flashcardBO = new FlashcardBO();
			int status = flashcardBO.updateFlashcard(flashcardID, word, word_type, meaning, part.getInputStream());
			if (status > 0) {
				int userID = Integer.parseInt(request.getParameter("userID"));
				ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
				ArrayList<Flashcard> myCards = flashcardBO.getAllFlashcards(userID);
				request.setAttribute("listFlashcard", flashcards);
				request.setAttribute("myCards", myCards);
				String destination = "/flashcard.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
				dispatcher.forward(request, response);
				System.out.println("flashcard updated successfully");
			} else {
				System.out.println("flashcard updating failed");
			}
		}
	}

	private void deleteFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		int flashcardID = Integer.parseInt(request.getParameter("flashcardID"));
		FlashcardBO flashcardBO = new FlashcardBO();
		int status = flashcardBO.deleteFlashcard(flashcardID);
		if (status > 0) {
			int userID = Integer.parseInt(request.getParameter("userID"));
			ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
			ArrayList<Flashcard> myCards = flashcardBO.getAllFlashcards(userID);
			request.setAttribute("listFlashcard", flashcards);
			request.setAttribute("myCards", myCards);
			String destination = "/flashcard.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
			dispatcher.forward(request, response);
			System.out.println("flashcard deleted successfully");
		} else {
			System.out.println("flashcard deletion failed");
		}
	}

	private void getAllFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FlashcardBO flashcardBO = new FlashcardBO();
		int userID = Integer.parseInt(request.getParameter("userID"));
		ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
		ArrayList<Flashcard> myCards = flashcardBO.getAllFlashcards(userID);
		request.setAttribute("listFlashcard", flashcards);
		request.setAttribute("myCards", myCards);
		String destination = "/flashcard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

	private void getRandomizedFlashcard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		FlashcardBO flashcardBO = new FlashcardBO();
		ArrayList<Flashcard> flashcards = flashcardBO.getRandomizedFlashcards(userID);
		ArrayList<Flashcard> myCards = flashcardBO.getAllFlashcards(userID);
		request.setAttribute("listFlashcard", flashcards);
		request.setAttribute("myCards", myCards);
		String destination = "/flashcard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}

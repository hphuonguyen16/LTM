package model.bo;

import java.util.ArrayList;

import model.bean.Point_Lesson;
import model.dao.PointDAO;

public class PointBO {

	PointDAO pointDAO = new PointDAO();

	public PointBO() {
		// TODO Auto-generated constructor stub
	}

	public int addNewPoint(int userID, int lessonID, int points) {
		return pointDAO.addNewPoint(userID, lessonID, points);
	}

	public ArrayList<Point_Lesson> getPointAndLessonByUserID(int userID) {
		return pointDAO.getPointAndLessonByUserID(userID);
	}
}

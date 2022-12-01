package model.bo;

import java.util.ArrayList;

import model.bean.Point;
import model.dao.PointDAO;

public class PointBO {

	PointDAO pointDAO = new PointDAO();

	public PointBO() {
		// TODO Auto-generated constructor stub
	}

	public int addNewPoint(int userID, int lessonID, int points) {
		return pointDAO.addNewPoint(userID, lessonID, points);
	}

	public ArrayList<Point> getAllPointsByUserID(int userID) {
		return pointDAO.getAllPointsByUserID(userID);
	}
}

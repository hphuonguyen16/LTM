package model.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Lesson;
import model.dao.LessonDAO;

public class LessonBO {
	LessonDAO lessonDAO;

	public LessonBO() {
		// TODO Auto-generated constructor stub
		lessonDAO = new LessonDAO();
	}
	public List<Lesson> getAllLessons() {
		return lessonDAO.getAllLessons();
	}
	public void AddNewLesson(String _topic,String _level) throws ClassNotFoundException {
		lessonDAO.AddNewLesson(_topic, _level);
	}
}

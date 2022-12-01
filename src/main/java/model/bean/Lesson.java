package model.bean;

public class Lesson {
	private int lessonID;
	private String topic;
	private int level;

	public Lesson() {
		// TODO Auto-generated constructor stub
	}
	

	public Lesson(int lessonID, String topic, int level) {
		super();
		this.lessonID = lessonID;
		this.topic = topic;
		this.level = level;
	}


	public int getLessonID() {
		return lessonID;
	}

	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	

}

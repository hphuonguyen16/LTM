package model.bean;

public class User {
	private int userID;
	private String username;
	private String password;
	private String role;
	private String name;

	public void _User(int _id,String _username,String _password,String _role,String _name)
	{
		userID = _id;
		username = _username;
		password = _password;
		role = _role;
		name =_name;
	}
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}

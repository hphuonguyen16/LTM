package model.bo;

import model.bean.User;
import model.dao.UserDAO;

public class UserBO {
	UserDAO userDao = new UserDAO();
	public User Login(String _username,String _password) throws ClassNotFoundException {
		return userDao.Login(_username, _password);
	}
	public void AddAccount(String _username,String _password,String _name) throws ClassNotFoundException {
		userDao.AddAccount(_username, _password,_name);
	}
	public void ChangePassword(String _username,String current_password) throws ClassNotFoundException {
		userDao.ChangePassword(_username, current_password);
	}
	public boolean isValidUser(String username, String password) {
		if(username == "" || password == "")
			return false;
			else return true;
	}
}

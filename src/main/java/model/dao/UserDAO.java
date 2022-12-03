package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import model.bean.Flashcard;
import model.bean.User;

public class UserDAO {
	public User Login(String _username, String _password) throws ClassNotFoundException { 
		User acc = new User();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elw", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where username = '" + _username + "' AND password = '" + _password +"'");
			if(rs.next())
			{
					acc.setUserID(rs.getInt(1));
					acc.setUsername(rs.getString(2));
					acc.setPassword(rs.getNString(3));
					acc.setRole(rs.getString(4));
					acc.setName(rs.getString(5));
				   return acc;
			   }
			else return null;
			} catch(SQLException e) {System.out.println(e);}
		return null;
	}
	public void AddAccount(String _username, String _password,String _name) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elw", "root", "");
			String sql = "insert into user(username,password,role,name) values( ?, ?, ?, ?)";
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1, _username);
			statement.setString(2, _password);
			statement.setString(3, "user");
			statement.setString(4, _name);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	public void ChangePassword(String _username, String new_password) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elw", "root", "");
			String sql = "update user set password = ? where username = ?";
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1, new_password);
			statement.setString(2, _username);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}

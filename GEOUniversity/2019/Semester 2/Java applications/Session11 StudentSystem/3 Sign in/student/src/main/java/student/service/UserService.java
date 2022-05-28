package student.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import student.model.User;

public interface UserService {
	
	public User login(String username,String password) throws SQLException;
	
	public boolean checkUser(String username) throws SQLException;
	
	public void save(User user) throws SQLException;
	
	public boolean checkStuid(String stuid) throws SQLException;

}

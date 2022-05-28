package student.service;

import org.springframework.stereotype.Service;

import student.model.User;

public interface UserService {
	
	public User login(String username,String password);

}

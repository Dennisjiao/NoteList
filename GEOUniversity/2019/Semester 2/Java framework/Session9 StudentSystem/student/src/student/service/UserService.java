package student.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import student.model.User;

public interface UserService {
	
	User findById(String key) throws DataAccessException;
	
	List<User> findAll();
	
	User findByStuId(String stuid) throws DataAccessException;
	
	int save(User object) throws DataAccessException;
	
	int update(User object) throws DataAccessException;
	
	int delete(User object) throws DataAccessException;
	
	User login(String username,String password) throws DataAccessException;
	
	Long getRowCount(User user) throws DataAccessException;
	
	List<User> query(int page,int limit,User object) throws DataAccessException;

}

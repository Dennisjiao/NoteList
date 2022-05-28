package student.service;

import java.sql.SQLException;
import java.util.List;


import student.model.User;

public interface UserService {
	
	public User getById(String username) throws SQLException;
	
	public Long getRowCount(User object) throws SQLException;
	
	public List<User> getList(int page,int limit,User user)  throws SQLException;
	
	public User login(String username,String password) throws SQLException;
	
	public boolean checkUser(String username) throws SQLException;
	
	public boolean checkStuid(String stuid,String username) throws SQLException;
	
	public void save(User user) throws SQLException;
	
	public void update(User user) throws SQLException;
	
	public void delete(User user) throws SQLException;
	
	public void saveOrUpdate(User user) throws SQLException;
	
	public int deleteByIds(String ids) throws SQLException;

}

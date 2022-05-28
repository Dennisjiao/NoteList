package student.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import student.model.User;

public interface UserService {
	
	@Cacheable(value="User",key="#username")
	public User getById(String username) throws SQLException;
	
	public Long getRowCount(User object) throws SQLException;
	
	public List<User> getList(int page,int limit,User user)  throws SQLException;
	
	@Cacheable(value="User",key="#username")
	public User login(String username,String password) throws SQLException;
	
	public boolean checkUser(String username) throws SQLException;
	
	public boolean checkStuid(String stuid,String username) throws SQLException;
	
	@CachePut(value="User", key="#user.username")
	public User save(User user) throws SQLException;
	
	@CachePut(value="User", key="#user.username")
	public User update(User user) throws SQLException;
	
	@CacheEvict(value="User",key="#user.username")
	public User delete(User user) throws SQLException;
	
	@CachePut(value="User", key="#user.username")
	public User saveOrUpdate(User user) throws SQLException;
	
	public int deleteByIds(String ids) throws SQLException;

}

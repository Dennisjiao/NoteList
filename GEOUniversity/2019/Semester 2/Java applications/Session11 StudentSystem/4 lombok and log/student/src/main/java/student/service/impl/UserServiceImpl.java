package student.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import student.dao.UserDao;
import student.model.User;
import student.service.UserService;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public User login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}

	@Override
	public boolean checkUser(String username) throws SQLException {
		// TODO Auto-generated method stub
		return dao.checkUser(username);
	}

	@Override
	public boolean checkStuid(String stuid) throws SQLException {
		// TODO Auto-generated method stub
		return dao.checkStuid(stuid);
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public void save(User user) throws SQLException {
		dao.persist(user);
		
	}

}

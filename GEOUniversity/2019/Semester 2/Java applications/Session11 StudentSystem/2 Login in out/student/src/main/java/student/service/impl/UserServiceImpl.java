package student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}

}

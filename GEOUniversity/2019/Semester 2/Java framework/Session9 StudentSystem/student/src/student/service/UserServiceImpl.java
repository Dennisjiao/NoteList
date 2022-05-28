package student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import student.dao.UserDao;
import student.model.User;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public User findById(String key) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findById(key);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	//返回-1代表用户已存在
	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int save(User object) throws DataAccessException {
		User find=findById(object.getUsername());
		if(find!=null)
			return -1;
		else
			return dao.save(object);
	}
	//返回-1代表用户不存在
	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int update(User object) throws DataAccessException {
		return dao.update(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int delete(User object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.delete(object);
	}

	@Override
	public User login(String username, String password) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}

	@Override
	public List<User> query(int page,int limit,User object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.query(page,limit,object);
	}

	@Override
	public User findByStuId(String stuid) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findByStuId(stuid);
	}

	@Override
	public Long getRowCount(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.getRowCount(user);
	}

}

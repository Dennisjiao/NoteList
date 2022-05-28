package student.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public User getById(String username) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getByKey(username);
	}
	
	//组合hql，type=1代表查询符合条件的总记录数，type=2代表查询所有符合条件的记录
	private String assembleHQL(User user,int type)
	{
		String sql;
		if(type==1)
			sql="select count(distinct a.username) from User a,Stuinfo b where a.stuinfo.stuid=b.stuid and 1=1";
		else
			sql="select distinct a from User a inner join fetch a.stuinfo b where 1=1";
		if(user!=null && user.getUsername() != null && !user.getUsername().equals("")){
			sql += " AND a.username LIKE '%" + user.getUsername() + "%'";
		}
		if(user!=null && user.getStuinfo()!=null && user.getStuinfo().getGender() != null && !user.getStuinfo().getGender().equals("")){
			sql += " AND b.gender='" + user.getStuinfo().getGender() + "'";
		}
		if(user!=null && user.getStuinfo()!=null && user.getStuinfo().getStuid() != null && !user.getStuinfo().getStuid().equals("")){
			sql += " AND b.stuid LIKE '%" + user.getStuinfo().getStuid() + "%'";
		}
		if(user!=null && user.getStuinfo()!=null && user.getStuinfo().getStuname() != null && !user.getStuinfo().getStuname().equals("")){
			sql += " AND b.stuname LIKE '%" + user.getStuinfo().getStuname() + "%'";
		}		
		if(type!=1)
			sql+=" order by b.stuid";
		
		return sql;
	}
	
	@Override
	public Long getRowCount(User object) throws SQLException {
		String hql=assembleHQL(object,1);
		return (Long)dao.QueryObjectByHQL(hql);
	}
	@Override
	public List<User> getList(int page,int limit,User user) throws SQLException {

		String hql=assembleHQL(user,2);
		if(limit>0)
		{
			if(page<1)page=1;
			int start=(page-1)*limit;
			return dao.QueryListByHQL(hql,start,limit);
		}
		else
		{
			return dao.QueryListByHQL(hql);
		}
	}
	
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
	public boolean checkStuid(String stuid,String username) throws SQLException {
		// TODO Auto-generated method stub
		User user=dao.checkStuid(stuid);
		return user!=null && !user.getUsername().equals(username);
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public void save(User user) throws SQLException {
		dao.persist(user);
		
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public void update(User user) throws SQLException {
		dao.update(user);
		
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public void delete(User user) throws SQLException {
		dao.delete(user);
		
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public void saveOrUpdate(User user) throws SQLException {
		dao.saveOrUpdate(user);
		
	}
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	@Override
	public int deleteByIds(String ids) throws SQLException {
		String hql="from User u inner join fetch u.stuinfo where username in ("+ids+")";
		List<User> list=dao.QueryListByHQL(hql);
		String stuids="";
		if(list.size()>0){
			for(User user:list){
				stuids=stuids+user.getStuinfo().getStuid()+",";
			}
			stuids=stuids.substring(0,stuids.length()-1);
			hql="delete from User where username in ("+ids+")";
			dao.DeleteByIds(hql);
			hql="delete from Stuinfo where stuid in ("+stuids+")";
			return dao.DeleteByIds(hql);
		}
		
		return 0;
	}

	

}

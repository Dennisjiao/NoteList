package student.dao;

import java.sql.SQLException;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import student.model.User;

@Repository
public class UserDao extends AbstractDao<String, User> {
	
	//用户登录
	public User login(String username,String password) throws SQLException
	{
		String hql="from User u left join fetch u.stuinfo s where u.username=:username and u.password=:password";
		@SuppressWarnings("unchecked")
		Query<User> query=getSession().createQuery(hql)
			.setParameter("username", username)
			.setParameter("password", password);
		
		return query.uniqueResult();
	}
	
	//检测用户是否已存在
	public boolean checkUser(String username) throws SQLException
	{
		String hql="from User u where u.username=:username";
		@SuppressWarnings("unchecked")
		Query<User> query=getSession().createQuery(hql)
			.setParameter("username", username);
		
		return query.uniqueResult()!=null;
	}
	//检测用户是否已存在
	public boolean checkStuid(String stuid) throws SQLException
	{
		String hql="from Stuinfo s where s.stuid=:stuid";
		@SuppressWarnings("unchecked")
		Query<User> query=getSession().createQuery(hql)
			.setParameter("stuid", stuid);
		
		return query.uniqueResult()!=null;
	}

}

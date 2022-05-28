package student.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import student.model.User;

@Repository
public class UserDao extends AbstractDao<String, User> {
	
	//用户登录
	public User login(String username,String password)
	{
		String hql="from User u left join fetch u.stuinfo s where u.username=:username and u.password=:password";
		@SuppressWarnings("unchecked")
		Query<User> query=getSession().createQuery(hql)
			.setParameter("username", username)
			.setParameter("password", password);
		
		return query.uniqueResult();
	}

}

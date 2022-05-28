package test;


import org.hibernate.Session;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Country;

public class test {

	@Test
	public void test01() {
		Session session1=HibernateSessionFactory.getSession();
		//将id为1的Country加载到一级缓存session1中，将引发select查询
		Country country1=session1.get(Country.class,1);
		System.out.println("cname1========= " + country1.getCname());
		//从一级缓存session1中读取id为1的Country对象，不会到db中查询，即不引发select执行
		Country country2=session1.get(Country.class,1);
		System.out.println("cname2========= " + country2.getCname());
		//session1关闭，一级缓存消失，session1中的Country对象也不存在了
		session1.close();
		
		Session session2=HibernateSessionFactory.getSession();
		//此处未启用二级缓存
		Country country3=session2.get(Country.class,1);
		System.out.println("cname3========= " + country3.getCname());
		session2.close();
				
	}
	
}

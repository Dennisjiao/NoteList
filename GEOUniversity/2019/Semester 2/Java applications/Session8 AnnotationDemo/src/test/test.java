package test;
import java.util.Date;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.TestInt;
import model.TestVarchar;

public class test {

private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=HibernateSessionFactory.getSession();
	}
	
//	@Test
//	public void test01() {
//		try{
//			session.beginTransaction();
//			
//			TestInt testInt=new TestInt("aaa");			
//			session.save(testInt);
//						
//			session.getTransaction().commit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}		
//	}
	@Test
	public void test02() {
		try{
			session.beginTransaction();
			
			TestVarchar test=new TestVarchar("aaa");			
			session.save(test);
						
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
	@Test
	public void test03() {
		try{
			session.beginTransaction();
			
			TestInt test=new TestInt("aaa",23,new Date());			
			session.save(test);
						
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
}

package test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.sql.JoinType;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Country;

public class TestTableJoin {

	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=HibernateSessionFactory.getSession();
	}
	
	@Test
	public void test01() {
		try{
			session.beginTransaction();
			String hql="from Country c left join c.ministers";
			@SuppressWarnings("unchecked")
			List<Object[]> list=session.createQuery(hql).list();
			for(Object[] objects:list)
			{
				System.out.println(objects[0]+":"+objects[1]);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	@Test
	public void test02() {
		try{
			session.beginTransaction();
			String hql="from Country c left join fetch c.ministers";
			@SuppressWarnings("unchecked")
			List<Country> list=session.createQuery(hql).list();
			for(Country country:list)
			{
				System.out.println(country);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	@Test
	public void test03() {
		try{
			session.beginTransaction();
			String hql="select distinct c from Country c left join fetch c.ministers";
			@SuppressWarnings("unchecked")
			List<Country> list=session.createQuery(hql).list();
			for(Country country:list)
			{
				System.out.println(country);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	@Test
	public void test04() {
		try{
			session.beginTransaction();			
			@SuppressWarnings("unchecked")
			List<Country> list=session.createCriteria(Country.class)
					.createCriteria("ministers",JoinType.LEFT_OUTER_JOIN)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
			for(Country country:list)
			{
				System.out.println(country);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	@Test
	public void test05() {
		try{
			session.beginTransaction();			
			@SuppressWarnings("unchecked")
			List<Country> list=session.getNamedQuery("queryAll").list();
			for(Country country:list)
			{
				System.out.println(country);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
}

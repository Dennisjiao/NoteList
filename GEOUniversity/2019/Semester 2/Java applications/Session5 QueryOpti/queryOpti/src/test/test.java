package test;

import java.util.Set;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Country;
import model.Minister;
import model.Student;


public class test {
	
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=HibernateSessionFactory.getSession();
	}
	
	/*
	 * 插入数据
	 */
	@Test
	public void test01() {
		try{
			session.beginTransaction();
			Student stu=new Student("aaa",21);			
			session.save(stu);
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * get加载
	 */
	@Test
	public void test02() {
		try{
			session.beginTransaction();
			Student stu=session.get(Student.class, 1);
			System.out.println("id="+stu.getSid());
			System.out.println("name="+stu.getSname());
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * load加载
	 */
	@Test
	public void test03() {
		try{
			session.beginTransaction();
			Student stu=session.load(Student.class, 1);
			System.out.println("id="+stu.getSid());
			System.out.println("name="+stu.getSname());
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * 情况 1：多端关联配置优化 fetch=“join”等价于fetch=FetchType.EAGER
	 */
	@Test
	public void test04() {
		try{
			session.beginTransaction();
			Country country=session.get(Country.class, 1);
			Set<Minister> ministers=country.getMinisters();
			//获取集合大小
			System.out.println("ministers size="+ministers.size());
			//获取集合详情
			for(Minister minister:ministers){
				System.out.println(minister);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * 情况 2：多端关联配置优化 fetch=“select” lazy=“true”等价于fetch=FetchType.LAZY
	 */
	@Test
	public void test05() {
		try{
			session.beginTransaction();
			Country country=session.get(Country.class, 1);
			Set<Minister> ministers=country.getMinisters();
			//获取集合大小
			System.out.println("ministers size="+ministers.size());
			//获取集合详情
			for(Minister minister:ministers){
				System.out.println(minister);
			}
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * 情况 1：单端关联配置优化 fetch=“join”等价于fetch=FetchType.EAGER
	 */
	@Test
	public void test06() {
		try{
			session.beginTransaction();
			Minister minister = session.get(Minister.class, 1);
			Country country=minister.getCountry();
			//获取country对象的名称
			System.out.println(country.getCid());
			System.out.println(country.getCname());
						
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	/*
	 * 情况 2：单端关联配置优化 fetch=“select” lazy=“true”等价于fetch=FetchType.LAZY
	 */
	@Test
	public void test07() {
		try{
			session.beginTransaction();
			Minister minister = session.get(Minister.class, 1);
			Country country=minister.getCountry();
			//获取country对象的名称
			System.out.println(country.getCid());
			System.out.println(country.getCname());
						
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
}

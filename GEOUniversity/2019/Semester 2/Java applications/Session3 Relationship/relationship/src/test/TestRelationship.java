package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Column;
import model.Country;
import model.Course;
import model.Minister;
import model.Student;

public class TestRelationship {

private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=HibernateSessionFactory.getSession();
	}
	/*
	 * 去掉cascade = CascadeType.ALL和mappedBy = "country"，即取消级联保存和多端维护，然后添加@JoinColumn(name="cid")
	 * 添加@JoinColumn(name="cid")可以避免生成中间表
	 */
	@Test
	public void test01() {
		try{
			session.beginTransaction();
			
			Minister minister1=new Minister(1,"aaa");
			Set<Minister> ministers=new HashSet<Minister>();
			ministers.add(minister1);
			
			Country country=new Country(1,"USA");
			country.setMinisters(ministers);			
			
			session.save(country);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	/*
	 * 测试类中在 save(country)之前，先执行 save(minister)；或在 country 的映射文件中的关
		联属性映射中增加级联操作 CascadeType.PERSIST。
	 */
	@Test
	public void test02() {
		try{
			session.beginTransaction();
			
			Minister minister1=new Minister(1,"aaa");
			Set<Minister> ministers=new HashSet<Minister>();
			ministers.add(minister1);
			
			Country country=new Country(1,"USA");
			country.setMinisters(ministers);
			
			//session.save(minister1);
			session.save(country);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	/*
	 * 在 country 的映射文件中的关联属性映射中增加控制反转设置mappedBy = "country",注意要删除@JoinColumn(name="cid")
	 * 在 country 的映射文件中的关联属性映射中增加设置级联操作，或测试类中进行save(minister)。
	 */
	@Test
	public void test03() {
		try{
			session.beginTransaction();
			
			Minister minister1=new Minister(1,"aaa");
			Set<Minister> ministers=new HashSet<Minister>();
			ministers.add(minister1);
			
			Country country=new Country(1,"USA");
			country.setMinisters(ministers);
			
			session.save(country);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	/*
	 * 在 country 的映射文件中的关联属性映射中增加控制反转设置mappedBy = "country"与级联操作CascadeType.ALL。
	 * 在 minister 的映射文件中增加<many-to-one/>标签。
	 * 测试类中增加 minister.setCountry(country)。
	 * 测试类中只 save(country)，而不进行 save(minister)。
	 */
	@Test
	public void test04() {
		try{
			session.beginTransaction();
			
			Minister minister1=new Minister(2,"aaa");
			Set<Minister> ministers=new HashSet<Minister>();			
			ministers.add(minister1);
			
			Country country=new Country(2,"USA");
			country.setMinisters(ministers);
			
			minister1.setCountry(country);
			session.save(country);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
		/*
		 * 在 country 的映射文件中的关联属性映射中增加控制反转设置mappedBy = "country"与级联操作CascadeType.ALL。
		 * 在 minister 的映射文件中增加<many-to-one/>标签中增加级联操作。
		 * 测试类中只 save(minister)，而不进行 save(country)。
		 */
//		@Test
//		public void test05() {
//			try{
//				session.beginTransaction();
//				
//				Minister minister1=new Minister(3,"aaa");
//				Set<Minister> ministers=new HashSet<Minister>();			
//				ministers.add(minister1);
//				
//				Country country=new Country(3,"USA");
//				country.setMinisters(ministers);
//				
//				minister1.setCountry(country);
//				session.save(minister1);
//				session.getTransaction().commit();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//				session.getTransaction().rollback();
//			}
//		}
	
	/*
	 * 自关联
	 * 在多端添加cascade = CascadeType.ALL
	 */
//	@Test
//	public void test06() {
//		try{
//			session.beginTransaction();
//			Column sportsColumn=new Column(1,"体育栏目");
//			
//			Column footballColumn=new Column(2,"足球栏目");
//			footballColumn.setParentColumn(sportsColumn);
//			
//			Column basketballColumn=new Column(3,"篮球栏目");
//			basketballColumn.setParentColumn(sportsColumn);
//			
//			session.save(footballColumn);
//			session.save(basketballColumn);
//			
//			session.getTransaction().commit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//	}
	
	/*
	 * n:1-单向关联
	 * 在 minister 的映射文件中增加<many-to-one/>标签。
	 * 在 country 的映射文件中只有普通属性映射。
	 * 测试类中只 save(minister)，而不进行 save(country)
	 */
//	@Test
//	public void test07() {
//		try{
//			session.beginTransaction();
//			
//			Country country=new Country(4,"USA");
//			
//			Minister minister1=new Minister(4,"aaa");
//			minister1.setCountry(country);			
//			
//			session.save(minister1);
//			session.getTransaction().commit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//	}
	
	/*
	 * n:m-单向关联
	 * 添加<property name="hbm2ddl.auto">update</property>
	 * 修改为<property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
	 * 在student实体类加添加private Set<Course> courses;
	 * 测试类中只 save(student)，而不进行 save(course)。
	 */
//	@Test
//	public void test08() {
//		try{
//			session.beginTransaction();
//			
//			Course course1=new Course(1,"struts2");
//			Course course2=new Course(2,"Hibernate");
//			
//			Student student=new Student(1,"aaa");
//			student.getCourses().add(course1);
//			student.getCourses().add(course2);
//			session.save(student);
//			session.getTransaction().commit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//	}
	/*
	 * n:m-双向关联
	 * 把course表做和student相同的配置
	 * 添加private Set<Student> students;属性
	 * 为students属性的getter方法上添加@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 为students属性的getter方法上添加@JoinTable(name = "student_course",
		joinColumns = {@JoinColumn(name = "c_id", referencedColumnName = "cid")},
		inverseJoinColumns = {@JoinColumn(name = "s_id", referencedColumnName ="sid")})
	 */
//	@Test
//	public void test09() {
//		try{
//			session.beginTransaction();
//			
//			Student student=new Student(2,"bbb");
//			Course course1=new Course(3,"Spring");
//						
//			course1.getStudents().add(student);
//			session.save(course1);
//			session.getTransaction().commit();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//	}
}

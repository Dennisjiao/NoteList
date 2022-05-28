package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Student;

public class MyTest {
	private Session session;
	
	@Before
	public void setUp()
	{
		session=HibernateSessionFactory.getSession();
	}
	
	//完成数据的插入
	@Test
	public void test00(){
		try{
			session.beginTransaction();
			for(int i=0;i<10;i++){
				Student student=new Student("n_"+i,15+i,75+i);
				session.save(student);
			}
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用SQLquery查询
	@Test
	public void test01_SQL(){
		try{
			session.beginTransaction();
			String sql="select * from student";
			List<Student> students=session.createSQLQuery(sql).addEntity(Student.class).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用HQLquery查询
	@Test
	public void test02_HQL(){
		try{
			session.beginTransaction();
			String hql="from Student";
			List<Student> students=session.createQuery(hql).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用Criteria查询
	@Test
	public void test03_QBC(){
		try{
			session.beginTransaction();			
			List<Student> students=session.createCriteria(Student.class).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用SQLquery查询,并排序
	@Test
	public void test04_SQL_OrderBy(){
		try{
			session.beginTransaction();
			String sql="select * from student order by score desc";
			List<Student> students=session.createSQLQuery(sql).addEntity(Student.class).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用HQLquery查询,并排序
	@Test
	public void test05_HQL(){
		try{
			session.beginTransaction();
			String hql="from Student order by score desc";
			List<Student> students=session.createQuery(hql).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用Criteria查询,并排序
	@Test
	public void test06_QBC(){
		try{
			session.beginTransaction();			
			List<Student> students=session.createCriteria(Student.class).addOrder(Order.desc("score")).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用SQL参数绑定查询
	@Test
	public void test07_SQL_SetXxx(){
		try{
			session.beginTransaction();
			String sql="select * from student where age>? and score<?";
			List<Student> students=session.createSQLQuery(sql)
					.addEntity(Student.class)
					.setInteger(0, 20)
					.setDouble(1, 98)
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用SQL参数绑定查询
	@Test
	public void test08_SQL_SetParameter1(){
		try{
			session.beginTransaction();
			String sql="select * from student where age>? and score<?";
			List<Student> students=session.createSQLQuery(sql)
					.addEntity(Student.class)
					.setParameter(0, 20)
					.setParameter(1, 98)
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用SQL参数绑定查询
	@Test
	public void test09_SQL_SetParameter2(){
		try{
			session.beginTransaction();
			String sql="select * from student where age>:tage and score<:tscore";
			List<Student> students=session.createSQLQuery(sql)
					.addEntity(Student.class)
					.setParameter("tage", 20)
					.setParameter("tscore", 98)
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用HQL参数绑定查询
	@Test
	public void test10_HQL_SetParameter(){
		try{
			session.beginTransaction();
			String sql="from Student where age>:tage and score<:tscore";
			List<Student> students=session.createQuery(sql)					
					.setParameter("tage", 20)
					.setParameter("tscore", 98.0)
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用Criteria查询参数绑定
	@Test
	public void test11_QBC(){
		try{
			session.beginTransaction();			
			List<Student> students=session.createCriteria(Student.class)
					.add(Restrictions.gt("age",20))
					.add(Restrictions.lt("score",98.0))
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用HQL分页查询
	@Test
	public void test12_HQL_PageQuery(){
		try{
			session.beginTransaction();
			String sql="from Student";
			List<Student> students=session.createQuery(sql)					
					.setFirstResult(3)
					.setMaxResults(5)
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	//使用HQL唯一性查询
	@Test
	public void test13_HQL_UniqueQuery(){
		try{
			session.beginTransaction();
			String sql="from Student where id=:tid";
			Student stu=(Student)session.createQuery(sql)					
					.setParameter("tid", 4)
					.uniqueResult();
			System.out.println(stu);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用Criteria查询聚合函数投影
	@Test
	public void test14_QBC(){
		try{
			session.beginTransaction();			
			Object obj=session.createCriteria(Student.class)
					.setProjection(Projections.count("id"))
					.uniqueResult();
			System.out.println(obj);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用SQL参数绑定查询
	@Test
	public void test15_SQL(){
		try{
			session.beginTransaction();
			String sql="select tname name,tage age from student";
			List<Student> students=session.createSQLQuery(sql)
					.setResultTransformer(Transformers.aliasToBean(Student.class))		
					.list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	//使用HQL查询
	@Test
	public void test16_HQL(){
		try{
			session.beginTransaction();
			String sql="select new Student(name,age) from Student";
			List<Student> students=session.createQuery(sql).list();
			students.forEach(System.out::println);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
}

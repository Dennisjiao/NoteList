package test;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import hsf.HibernateSessionFactory;
import model.Student;

public class test {

private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=HibernateSessionFactory.getSession();
	}
	
	@Test
	public void test01() {
		try{
			session.beginTransaction();
			
			Student student=new Student(3,"张三",23);			
			session.save(student);
						
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
	@Test
	public void test02() {
		try{
			session.beginTransaction();
			
			Student student=session.get(Student.class, 3);
			student.setSname("王五");
			System.out.println(student);
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
			//添加排他锁
			//Student student=session.get(Student.class, 1,LockMode.UPGRADE);
			//添加共享锁
			Student student=session.get(Student.class, 1,LockMode.READ);
			
			//做session中的修改操作
			//student.setSname("田七");
			student.setSname("田七1");
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
	
}
/*
 * Hibernate的org.hibernate.LockMode枚举类

    LockMode.READ，事务的隔离级别是Repeatable Read或Serializable时，请求读取数据库记录时自动获得
    LockMode.WRITE，请求插入或更新数据库记录时自动获得
    LockMode.OPTIMISTIC，乐观锁
    LockMode.OPTIMISTIC_FORCE_INCREMENT，乐观锁
    LockMode.PESSIMISTIC_READ，与LockMode.PESSIMISTIC_WRITE相同
    LockMode.PESSIMISTIC_WRITE，事务开始即获得数据库的锁
    LockMode.PESSIMISTIC_FORCE_INCREMENT，事务开始即设置version
    LockMode.UPGRADE，已过时，应用的查询请求使用SELECT ... FOR UPDATE时获得
    LockMode.UPGRADE_NOWAIT，Oracle应用的查询请求使用SELECT ... FOR UPDATE NOWAIT时获得
    LockMode.UPGRADE_SKIPLOCKED，Oracle应用的查询请求使用SELECT ... FOR UPDATE SKIP LOCKED时获得，SQL Server应用的查询请求使用SELECT ... with (rowlock, updlock, readpast)时获得
    LockMode.FORCE，已过时，被PESSIMISTIC_FORCE_INCREMENT替换
    LockMode.NONE，取消任何锁，如事务结束后的所有对象，或执行了Session的update()、saveOrUpdate()的对象
*/

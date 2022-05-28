import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Student;

public class Test {

	public static void main(String[] args) {
		Configuration configure=new Configuration().configure();
		SessionFactory sessionFactory=configure.buildSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		try{
			session.beginTransaction();
			Student student=new Student("张三",23,93.5);
			session.save(student);
			session.getTransaction().commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}		

	}

}

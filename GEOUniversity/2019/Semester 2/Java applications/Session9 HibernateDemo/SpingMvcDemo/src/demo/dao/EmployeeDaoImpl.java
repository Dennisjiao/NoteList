package demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import demo.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	//获取当前线程绑定的session
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
		
	public Employee findById(int id) {		
		return getSession().get(Employee.class, id);
		
	}

	public void saveEmployee(Employee employee) {
		getSession().save(employee);
	}

	public void deleteEmployeeBySsn(String ssn) {
		Session session=getSession();
		String hql="delete from Employee where ssn=:ssn";
		Query query=session.createQuery(hql);
		query.setParameter("ssn", ssn);
		query.executeUpdate();
				
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Session session=getSession();
		String hql="from Employee";
		Query query=session.createQuery(hql);
		return query.list();
		
	}

	public Employee findEmployeeBySsn(String ssn) {
		Employee employee=null;
		Session session=getSession();
		String hql="from Employee where ssn=:ssn";
		Query query=session.createQuery(hql);
		query.setParameter("ssn", ssn);
		
		return (Employee) query.uniqueResult();
	}
}

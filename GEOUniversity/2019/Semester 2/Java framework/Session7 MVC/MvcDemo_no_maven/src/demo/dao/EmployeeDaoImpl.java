package demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import demo.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public Employee findById(int id) {
		Employee employee=null;
		try{
			employee=jdbc.queryForObject("select * from EMPLOYEE where id=?",new EmployeeRowMapper(), id);
		}
		catch (EmptyResultDataAccessException e) {
			employee=null;
        }
		return employee;
		
	}

	public void saveEmployee(Employee employee) {
		jdbc.update(
				"insert into employee (name,joining_date,salary,ssn)" + " values (?, ?, ?, ?)",
				employee.getName(), employee.getJoiningDate(), employee.getSalary(), employee.getSsn());
		
	}

	public void deleteEmployeeBySsn(String ssn) {
		jdbc.update("delete from employee where ssn=?",ssn);
	}

	public List<Employee> findAllEmployees() {
		return jdbc.query("select * from EMPLOYEE order by id asc", new EmployeeRowMapper());
	}

	public Employee findEmployeeBySsn(String ssn) {
		Employee employee=null;
		try{
			employee=jdbc.queryForObject("select * from EMPLOYEE where ssn=?",new EmployeeRowMapper(), ssn);
		}
		catch (EmptyResultDataAccessException e) {
			employee=null;
        }
		return employee;
	}
	
	private static class EmployeeRowMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Employee(rs.getInt("id"), rs.getString("name"), rs.getDate("joining_date"), rs.getDouble("salary"),
					rs.getString("ssn"));
		}
	}
}

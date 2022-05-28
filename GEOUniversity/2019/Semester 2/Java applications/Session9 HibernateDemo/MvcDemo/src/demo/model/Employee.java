package demo.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Employee {

	private int id;

	@Size(min=3, max=50)	
	private String name;

	//@DatetimeFormat是将String转换成Date，一般前台给后台传值时用
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date joiningDate;

	@NotNull	
	private Double salary;
	
	@NotEmpty	
	private String ssn;

	public Employee()
	{
		
	}
	public Employee(int id,String name,Date joiningDate,Double salary,String ssn)
	{
		this.id=id;
		this.name=name;
		this.joiningDate=joiningDate;
		this.salary=salary;
		this.ssn=ssn;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joiningDate="
				+ joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
	}
	
	
	

}

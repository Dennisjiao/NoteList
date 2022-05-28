package demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "ssn"))

public class Employee implements java.io.Serializable {

	// Fields

	private Integer id;
	@Size(min=3, max=50)
	private String name;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joiningDate;
	@NotNull
	private double salary;
	@NotEmpty
	private String ssn;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(String name, Date joiningDate, double salary, String ssn) {
		this.name = name;
		this.joiningDate = joiningDate;
		this.salary = salary;
		this.ssn = ssn;
	}
	public Employee(int id,String name,Date joiningDate,Double salary,String ssn)
	{
		this.id=id;
		this.name=name;
		this.joiningDate=joiningDate;
		this.salary=salary;
		this.ssn=ssn;
	}
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "joining_date", nullable = false, length = 10)

	public Date getJoiningDate() {
		return this.joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Column(name = "salary", nullable = false, precision = 22, scale = 0)

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Column(name = "ssn", unique = true, nullable = false, length = 30)

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}
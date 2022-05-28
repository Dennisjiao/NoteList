package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * TestInt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "test_int", catalog = "test")

public class TestInt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;	
	private Integer age;
	
	private Date updateTime;

	// Constructors

	/** default constructor */
	public TestInt() {
	}

	/** full constructor */
	public TestInt(String name, Integer age, Date updateTime) {
		this.name = name;
		this.age = age;
		this.updateTime = updateTime;
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

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//@Transient不能放到属性上，否则不起作用
	@Column(name = "age")
	@Transient  
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "update_time", length = 10)

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
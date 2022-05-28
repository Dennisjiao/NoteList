package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.jpamodelgen.xml.jaxb.FetchType;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "test")

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sname;
	private Integer sage;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(String sname, Integer sage) {
		this.sname = sname;
		this.sage = sage;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "sid", unique = true, nullable = false)

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "sname")

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Column(name = "sage")

	public Integer getSage() {
		return this.sage;
	}

	public void setSage(Integer sage) {
		this.sage = sage;
	}

}
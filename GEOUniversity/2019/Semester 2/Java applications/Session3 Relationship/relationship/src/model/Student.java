package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "test")

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sname;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Student(Integer sid, String sname) {
		this.sid = sid;
		this.sname = sname;
	}

	// Property accessors
	@Id

	@Column(name = "sid", unique = true, nullable = false)

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "sname", length = 45)

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}
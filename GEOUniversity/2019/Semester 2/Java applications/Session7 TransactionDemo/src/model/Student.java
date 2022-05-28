package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "test")

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Integer version;
	private String sname;
	private Integer sage;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Student(Integer sid, String sname, Integer sage) {
		this.sid = sid;
		this.sname = sname;
		this.sage = sage;
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

	@Version
	@Column(name = "version")

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
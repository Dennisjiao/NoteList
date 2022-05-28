package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course", catalog = "test")

public class Course implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	
	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;		
	}

	// Property accessors
	@Id

	@Column(name = "cid", unique = true, nullable = false, length = 32)

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "cname", nullable = false, length = 30)

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	
}
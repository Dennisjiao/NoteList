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
 * Country entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "country", catalog = "test")

public class Country implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private Set<Minister> ministers = new HashSet<Minister>(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Integer cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Country(Integer cid, String cname, Set<Minister> ministers) {
		this.cid = cid;
		this.cname = cname;
		this.ministers = ministers;
	}

	// Property accessors
	@Id

	@Column(name = "cid", unique = true, nullable = false)

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "cname", length = 45)

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")

	public Set<Minister> getMinisters() {
		return this.ministers;
	}

	public void setMinisters(Set<Minister> ministers) {
		this.ministers = ministers;
	}

}
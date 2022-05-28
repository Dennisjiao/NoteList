package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Minister entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "minister", catalog = "test")

public class Minister implements java.io.Serializable {

	// Fields

	private Integer mid;
	private Country country;
	private String mname;

	// Constructors

	/** default constructor */
	public Minister() {
	}

	/** minimal constructor */
	public Minister(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public Minister(Integer mid, Country country, String mname) {
		this.mid = mid;
		this.country = country;
		this.mname = mname;
	}

	// Property accessors
	@Id

	@Column(name = "mid", unique = true, nullable = false)

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "mname", length = 45)

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "Minister [mid=" + mid + ", mname=" + mname + "]";
	}

}
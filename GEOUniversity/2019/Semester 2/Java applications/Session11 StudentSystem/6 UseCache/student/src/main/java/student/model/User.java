package student.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "test")

public class User implements java.io.Serializable {

	// Fields

	private String username;
	private Stuinfo stuinfo;
	@JSONField(serialize = false)
	private String password;
	@JSONField(serialize = false)
	private String rpasswd;
	private short usertype;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, short usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	/** full constructor */
	public User(String username, Stuinfo stuinfo, String password, short usertype) {
		this.username = username;
		this.stuinfo = stuinfo;
		this.password = password;
		this.usertype = usertype;
	}

	// Property accessors
	@Id

	@Column(name = "username", unique = true, nullable = false, length = 40)

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "stuid")

	public Stuinfo getStuinfo() {
		return this.stuinfo;
	}

	public void setStuinfo(Stuinfo stuinfo) {
		this.stuinfo = stuinfo;
	}

	@Column(name = "password", nullable = false, length = 20)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "usertype", nullable = false)

	public short getUsertype() {
		return this.usertype;
	}

	public void setUsertype(short usertype) {
		this.usertype = usertype;
	}
	@Transient
	public String getRpasswd() {
		return rpasswd;
	}

	public void setRpasswd(String rpasswd) {
		this.rpasswd = rpasswd;
	}

}
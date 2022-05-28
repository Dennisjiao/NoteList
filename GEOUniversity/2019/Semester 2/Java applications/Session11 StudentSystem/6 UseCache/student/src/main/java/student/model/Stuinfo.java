package student.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Stuinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stuinfo", catalog = "test")

public class Stuinfo implements java.io.Serializable {

	// Fields

	private String stuid;
	private String stuname;
	private String gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String classname;
	private String address;
	@JSONField(serialize = false)
	private Set<CourseChoosing> courseChoosings = new HashSet<CourseChoosing>(0);
	@JSONField(serialize = false)
	private Set<User> users = new HashSet<User>(0);
	@JSONField(serialize = false)
	private Set<Score> scores = new HashSet<Score>(0);

	// Constructors

	/** default constructor */
	public Stuinfo() {
	}

	/** minimal constructor */
	public Stuinfo(String stuid, String stuname) {
		this.stuid = stuid;
		this.stuname = stuname;
	}

	/** full constructor */
	public Stuinfo(String stuid, String stuname, String gender, Date birthday, String classname, String address,
			Set<CourseChoosing> courseChoosings, Set<User> users, Set<Score> scores) {
		this.stuid = stuid;
		this.stuname = stuname;
		this.gender = gender;
		this.birthday = birthday;
		this.classname = classname;
		this.address = address;
		this.courseChoosings = courseChoosings;
		this.users = users;
		this.scores = scores;
	}

	// Property accessors
	@Id

	@Column(name = "stuid", unique = true, nullable = false, length = 20)

	public String getStuid() {
		return this.stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	@Column(name = "stuname", nullable = false, length = 20)

	public String getStuname() {
		return this.stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	@Column(name = "gender", length = 2)

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "classname", length = 20)

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "address", length = 50)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stuinfo")

	public Set<CourseChoosing> getCourseChoosings() {
		return this.courseChoosings;
	}

	public void setCourseChoosings(Set<CourseChoosing> courseChoosings) {
		this.courseChoosings = courseChoosings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stuinfo")

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stuinfo")

	public Set<Score> getScores() {
		return this.scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

}
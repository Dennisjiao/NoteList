package student.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course", catalog = "test")

public class Course implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private Set<CourseChoosing> courseChoosings = new HashSet<CourseChoosing>(0);
	private Set<Score> scores = new HashSet<Score>(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Course(String cid, String cname, Set<CourseChoosing> courseChoosings, Set<Score> scores) {
		this.cid = cid;
		this.cname = cname;
		this.courseChoosings = courseChoosings;
		this.scores = scores;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")

	public Set<CourseChoosing> getCourseChoosings() {
		return this.courseChoosings;
	}

	public void setCourseChoosings(Set<CourseChoosing> courseChoosings) {
		this.courseChoosings = courseChoosings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")

	public Set<Score> getScores() {
		return this.scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

}
package student.model;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CourseChoosing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_choosing", catalog = "test")

public class CourseChoosing implements java.io.Serializable {

	// Fields

	private CourseChoosingId id;
	private Course course;
	private Stuinfo stuinfo;
	private Date cdate;

	// Constructors

	/** default constructor */
	public CourseChoosing() {
	}

	/** full constructor */
	public CourseChoosing(CourseChoosingId id, Course course, Stuinfo stuinfo, Date cdate) {
		this.id = id;
		this.course = course;
		this.stuinfo = stuinfo;
		this.cdate = cdate;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "stuid", column = @Column(name = "stuid", nullable = false, length = 20)),
			@AttributeOverride(name = "cid", column = @Column(name = "cid", nullable = false, length = 32)) })

	public CourseChoosingId getId() {
		return this.id;
	}

	public void setId(CourseChoosingId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false, insertable = false, updatable = false)

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stuid", nullable = false, insertable = false, updatable = false)

	public Stuinfo getStuinfo() {
		return this.stuinfo;
	}

	public void setStuinfo(Stuinfo stuinfo) {
		this.stuinfo = stuinfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cdate", nullable = false, length = 10)

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

}
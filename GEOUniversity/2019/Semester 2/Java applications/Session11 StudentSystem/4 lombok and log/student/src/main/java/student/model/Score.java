package student.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "score", catalog = "test")

public class Score implements java.io.Serializable {

	// Fields

	private String id;
	private Course course;
	private Stuinfo stuinfo;
	private double score;
	private Date sdate;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** full constructor */
	public Score(Course course, Stuinfo stuinfo, double score, Date sdate) {
		this.course = course;
		this.stuinfo = stuinfo;
		this.score = score;
		this.sdate = sdate;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false, length = 32)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stuid", nullable = false)

	public Stuinfo getStuinfo() {
		return this.stuinfo;
	}

	public void setStuinfo(Stuinfo stuinfo) {
		this.stuinfo = stuinfo;
	}

	@Column(name = "score", nullable = false, precision = 4, scale = 1)

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sdate", nullable = false, length = 10)

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

}
package student.model;

import java.util.Date;

public class Score {
	
	private String id;
	private Course course;
	private StuInfo stuinfo;
	private Double score;
	private Date sdate;
	public Score() {
		super();
	}
	public Score(String id, Course course, StuInfo stuinfo, Double score, Date sdate) {
		super();
		this.id = id;
		this.course = course;
		this.stuinfo = stuinfo;
		this.score = score;
		this.sdate = sdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public StuInfo getStuinfo() {
		return stuinfo;
	}
	public void setStuinfo(StuInfo stuinfo) {
		this.stuinfo = stuinfo;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "Score [id=" + id + ", course=" + course + ", stuinfo=" + stuinfo + ", score=" + score + ", sdate="
				+ sdate + "]";
	}
	

}

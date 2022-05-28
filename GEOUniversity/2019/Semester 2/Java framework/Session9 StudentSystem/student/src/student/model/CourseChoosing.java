package student.model;

import java.util.Date;

public class CourseChoosing {
	
	private CourseChoosingId id;
	private Date cdate;
	private CourseChoosingId old_cid;
	private StuInfo stuinfo;
	private Course course;
	public CourseChoosing() {
		super();
	}
	
	
	public CourseChoosing(CourseChoosingId id,StuInfo stuinfo,Course course, Date cdate) {
		super();
		this.id = id;
		this.cdate = cdate;
		this.setStuinfo(stuinfo);
		this.course = course;
	}


	public CourseChoosing(CourseChoosingId id, Date cdate, CourseChoosingId old_cid, StuInfo stuinfo, Course course) {
		super();
		this.id = id;
		this.cdate = cdate;
		this.old_cid = old_cid;
		this.setStuinfo(stuinfo);
		this.course = course;
	}


	public CourseChoosingId getId() {
		return id;
	}
	public void setId(CourseChoosingId id) {
		this.id = id;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public CourseChoosingId getOld_cid() {
		return old_cid;
	}
	public void setOld_cid(CourseChoosingId old_cid) {
		this.old_cid = old_cid;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "CourseChoosing [id=" + id + ", cdate=" + cdate + ", old_cid=" + old_cid + ", course=" + course + "]";
	}


	public StuInfo getStuinfo() {
		return stuinfo;
	}


	public void setStuinfo(StuInfo stuinfo) {
		this.stuinfo = stuinfo;
	}

	
}

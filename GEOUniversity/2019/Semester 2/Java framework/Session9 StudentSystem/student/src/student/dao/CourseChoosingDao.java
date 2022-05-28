package student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import student.model.Course;
import student.model.CourseChoosing;
import student.model.CourseChoosingId;
import student.model.StuInfo;

@Repository
public class CourseChoosingDao implements Dao<CourseChoosingId, CourseChoosing> {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public CourseChoosing findById(CourseChoosingId key) throws DataAccessException {
		CourseChoosing cc=null;
		try{
			cc=jdbc.queryForObject("select b.stuid,b.stuname,a.cid,cname,cdate from course a,stuinfo b,course_choosing c "
				                       + "where a.cid=c.cid and b.stuid=c.stuid and b.stuid=? and c.cid=?",new CourseChoosingRowMapper(), key.getStuid(),key.getCid());
		}
		catch (EmptyResultDataAccessException e) {
			cc=null;
        }
		return cc;
	}

	@Override
	public List<CourseChoosing> findAll() throws DataAccessException {
		return jdbc.query("select b.stuid,b.stuname,a.cid,cname,cdate from course a,stuinfo b,course_choosing c "
				                       + "where a.cid=c.cid and b.stuid=c.stuid", new CourseChoosingRowMapper());
	}
	
	@Override
	public int save(CourseChoosing object) throws DataAccessException {
		return jdbc.update(
				"insert into course_choosing values(?,?,?)",
				object.getId().getStuid(),object.getId().getCid(),object.getCdate());
	}

	@Override
	public int update(CourseChoosing object) throws DataAccessException {
		jdbc.update(
				"delete from course_choosing where stuid=? and cid=?",
				object.getOld_cid().getStuid(),object.getOld_cid().getCid());
		java.sql.Date sqlDate=new java.sql.Date(System.currentTimeMillis());
		return jdbc.update(
				"insert into course_choosing values(?,?,?)",
				object.getId().getStuid(),object.getId().getCid(),sqlDate);
	}

	@Override
	public int delete(CourseChoosing object) throws DataAccessException {
		jdbc.update(
				"delete from score where stuid=? and cid=?",
				object.getId().getStuid(),object.getId().getCid());
		return jdbc.update(
				"delete from course_choosing where stuid=? and cid=?",
				object.getId().getStuid(),object.getId().getCid());		
		
	}
	private static class CourseChoosingRowMapper implements RowMapper<CourseChoosing> {
		public CourseChoosing mapRow(ResultSet rs, int rowNum) throws SQLException {
			CourseChoosing cc=new CourseChoosing();
			CourseChoosingId id=new CourseChoosingId(rs.getString("stuid"),rs.getString("cid"));
			cc.setId(id);
			cc.setCdate(rs.getDate("cdate"));
			StuInfo stuinfo=new StuInfo();
			stuinfo.setStuid(rs.getString("stuid"));
			stuinfo.setStuname(rs.getString("stuname"));
			cc.setStuinfo(stuinfo);
			Course course=new Course(rs.getString("cid"),rs.getString("cname"));
			cc.setCourse(course);
			return cc;
		}
	}
	private static class CourseRowMapper implements RowMapper<Course> {
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			Course course=new Course();
			course.setCid(rs.getString("cid"));
			course.setCname(rs.getString("cname"));
			return course;
		}
	}
	//查找所有课程
	public List<Course> findAllCourses() throws DataAccessException {
		return jdbc.query("select * from course order by cid asc", new CourseRowMapper());
	}
	//查询指定学生的选课信息
	public List<CourseChoosing> findByStuID(String stuid) throws DataAccessException {
		return jdbc.query("select stuinfo.stuid,stuname,course.cid,cname,cdate from stuinfo,course_choosing,course "+
							"where stuinfo.stuid=? and stuinfo.stuid=course_choosing.stuid "+
							"and course.cid=course_choosing.cid", new CourseChoosingRowMapper(),stuid);
	}
	//根据学号，课程号查找是否已有成绩
	public boolean findScoreByID(String stuid,String cid) throws DataAccessException
	{
		Long count=0L;
		try{
			count=jdbc.queryForObject("select count(stuid) score where stuid=? and cid=?",Long.class,stuid,cid);
		}
		catch (EmptyResultDataAccessException e) {
			count=0L;
        }
		return count>0?true:false;
		
	}
	//分页查询，limit<=0代表不分页
	public List<CourseChoosing> query(int page,int limit)
	{
		String sql="select stuinfo.stuid,stuname,course.cid,cname,cdate from stuinfo,course_choosing,course "
				+ "where stuinfo.stuid=course_choosing.stuid and course.cid=course_choosing.cid "
				+ "order by course_choosing.stuid,course_choosing.cid";
		if(limit>0)
		{
			if(page<1)page=1;
			int start=(page-1)*limit;
			sql+=" limit "+start+","+limit;
		}
		return jdbc.query(sql, new CourseChoosingRowMapper());
	}
}

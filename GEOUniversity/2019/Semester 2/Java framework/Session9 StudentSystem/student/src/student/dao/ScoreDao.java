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
import student.model.Score;
import student.model.StuInfo;

@Repository
public class ScoreDao implements Dao<String, Score> {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Score findById(String key) throws DataAccessException {
		Score score=null;
		try{
			score=jdbc.queryForObject("select id,a.cid,a.cname,b.stuid,b.stuname,score,sdate from course a,stuinfo b,score c "
                    + "where a.cid=c.cid and b.stuid=c.stuid and id=?",new ScoreRowMapper(), key);
		}
		catch (EmptyResultDataAccessException e) {
			score=null;
        }
		return score;
	}

	@Override
	public List<Score> findAll() throws DataAccessException {
		return jdbc.query("select id,a.cid,cname,b.stuid,b.stuname,score,sdate from course a,stuinfo b,score c "
                + "where a.cid=c.cid and b.stuid=c.stuid order by b.stuid asc,a.cid asc", new ScoreRowMapper());
	}

	@Override
	public int save(Score object) throws DataAccessException {
		return jdbc.update(
				"insert into score(id,cid,stuid,score,sdate) values(?,?,?,?,?)",
				object.getId(),object.getCourse().getCid(),object.getStuinfo().getStuid(),object.getScore(),object.getSdate());
	}

	@Override
	public int update(Score object) throws DataAccessException {
		return jdbc.update(
				"update score set score=? where id=?",
				object.getScore(),object.getId());
	}

	@Override
	public int delete(Score object) throws DataAccessException {
		return jdbc.update(
				"delete from score where id=?",
				object.getId());	
	}
	private static class ScoreRowMapper implements RowMapper<Score> {
		public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
			Score score=new Score();
			score.setId(rs.getString("id"));
			score.setScore(rs.getDouble("score"));
			score.setSdate(rs.getDate("sdate"));
			StuInfo stuinfo=new StuInfo();
			stuinfo.setStuid(rs.getString("stuid"));
			stuinfo.setStuname(rs.getString("stuname"));
			score.setStuinfo(stuinfo);
			Course course=new Course(rs.getString("cid"),rs.getString("cname"));
			score.setCourse(course);
			return score;
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
	//查询是否存在选课信息
	public boolean CourseChoosingInfoExist(Score score)throws DataAccessException {
		Long count=0L;
		try{
			count=jdbc.queryForObject("select count(stuid) from course_choosing where stuid=? and cid=?",
					Long.class,score.getStuinfo().getStuid(),score.getCourse().getCid());
		}
		catch (EmptyResultDataAccessException e) {
			count=0L;
        }
		return count>0?true:false;
	}
	//分页查询，limit<=0代表不分页
	public List<Score> query(int page,int limit,Score score) throws DataAccessException 
	{
		String sql="SELECT id,a.cid,cname,b.stuid,b.stuname,score,sdate from course a,stuinfo b,score c "
				+ "WHERE a.cid=c.cid and b.stuid=c.stuid";
		if(score.getCourse().getCid() != null && !score.getCourse().getCid().equals("")){
			sql += " AND a.cid LIKE '%" + score.getCourse().getCid() + "%'";
		}	
		if(score.getCourse().getCname() != null && !score.getCourse().getCname().equals("")){
			sql += " AND cname LIKE '%" + score.getCourse().getCname() + "%'";
		}
		if(score.getStuinfo().getStuid() != null && !score.getStuinfo().getStuid().equals("")){
			sql += " AND b.stuid LIKE '%" + score.getStuinfo().getStuid() + "%'";
		}
		if(score.getStuinfo().getStuname() != null && !score.getStuinfo().getStuname().equals("")){
			sql += " AND stuname='" + score.getStuinfo().getStuname() + "'";
		}
		sql +=" order by b.stuid,a.cid ";
		if(limit>0)
		{
			if(page<1)page=1;
			int start=(page-1)*limit;
			sql+=" limit "+start+","+limit;
		}
		return jdbc.query(sql, new ScoreRowMapper());
	}
}

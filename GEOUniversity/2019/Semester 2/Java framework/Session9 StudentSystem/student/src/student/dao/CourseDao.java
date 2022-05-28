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

@Repository
public class CourseDao implements Dao<String, Course> {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Course findById(String key) throws DataAccessException {
		Course course=null;
		try{
			course=jdbc.queryForObject("select * from course where cid=?",new CourseRowMapper(), key);
		}
		catch (EmptyResultDataAccessException e) {
			course=null;
        }
		return course;
	}

	@Override
	public List<Course> findAll() throws DataAccessException {
		return jdbc.query("select * from course order by cid asc", new CourseRowMapper());
	}

	@Override
	public int save(Course object) throws DataAccessException {
		return jdbc.update(
				"insert into course(cid,cname) values (?, ?)",
				object.getCid(),object.getCname());
	}

	@Override
	public int update(Course object) throws DataAccessException {
		return jdbc.update(
				"update course set cname=? where cid=?",
				object.getCname(),object.getCid());
	}

	@Override
	public int delete(Course object) throws DataAccessException {
		jdbc.update(
				"delete from score where cid=?",
				object.getCid());
		jdbc.update(
				"delete from course_choosing where cid=?",
				object.getCid());
		
		return jdbc.update(
				"delete from course where cid=?",
				object.getCid());
	}
	private static class CourseRowMapper implements RowMapper<Course> {
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			Course course=new Course();
			course.setCid(rs.getString("cid"));
			course.setCname(rs.getString("cname"));
			return course;
		}
	}
	public List<Course> query(Course course) throws DataAccessException
	{
		String sql="SELECT * FROM course WHERE 1=1";
		if(course.getCid() != null && !course.getCid().equals("")){
			sql += " AND cid LIKE '%" + course.getCid() + "%'";
		}
		if(course.getCname() != null && !course.getCname().equals("")){
			sql += " AND cname='" + course.getCname() + "'";
		}
		sql+=" order by cid asc";
		return jdbc.query(sql, new CourseRowMapper());
	}
}

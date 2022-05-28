package student.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import student.model.Course;
import student.model.CourseChoosing;
import student.model.CourseChoosingId;

public interface CourseChoosingService {
	
	CourseChoosing findById(CourseChoosingId key) throws DataAccessException;
	
	List<CourseChoosing> findAll() throws DataAccessException;
	
	List<Course> findAllCourses() throws DataAccessException;
	
	boolean findScoreByID(String stuid,String cid) throws DataAccessException;
	
	int save(CourseChoosing object) throws DataAccessException;
	
	int update(CourseChoosing object) throws DataAccessException;
	
	int delete(CourseChoosing object) throws DataAccessException;
	
	List<CourseChoosing> query(int page,int limit) throws DataAccessException;

}

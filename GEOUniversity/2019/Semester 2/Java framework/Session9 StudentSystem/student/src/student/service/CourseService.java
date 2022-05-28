package student.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import student.model.Course;

public interface CourseService {
	
	Course findById(String key) throws DataAccessException;
	
	List<Course> findAll();
	
	int save(Course object) throws DataAccessException;
	
	int update(Course object) throws DataAccessException;
	
	int delete(Course object) throws DataAccessException;
	
	List<Course> query(Course object) throws DataAccessException;

}

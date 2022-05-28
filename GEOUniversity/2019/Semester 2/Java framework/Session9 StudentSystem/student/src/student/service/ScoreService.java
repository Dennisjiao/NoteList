package student.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import student.model.Course;
import student.model.Score;

public interface ScoreService {
	
	Score findById(String key) throws DataAccessException;
	
	List<Score> findAll() throws DataAccessException;
	
	List<Course> findAllCourses() throws DataAccessException;
	
	boolean CourseChoosingInfoExist(Score score)throws DataAccessException;
	
	int save(Score object) throws DataAccessException;
	
	int update(Score object) throws DataAccessException;
	
	int delete(Score object) throws DataAccessException;
	
	List<Score> query(int page,int limit,Score score) throws DataAccessException;

}

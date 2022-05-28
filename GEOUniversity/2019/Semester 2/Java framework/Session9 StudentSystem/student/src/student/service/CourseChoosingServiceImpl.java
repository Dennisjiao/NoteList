package student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import student.dao.CourseChoosingDao;
import student.model.Course;
import student.model.CourseChoosing;
import student.model.CourseChoosingId;

@Service
@Transactional(readOnly=true)
public class CourseChoosingServiceImpl implements CourseChoosingService {

	@Autowired
	CourseChoosingDao dao;
	
	@Override
	public CourseChoosing findById(CourseChoosingId key) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findById(key);
	}

	@Override
	public List<CourseChoosing> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Course> findAllCourses() throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findAllCourses();
	}

	@Override
	public boolean findScoreByID(String stuid, String cid) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findScoreByID(stuid, cid);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int save(CourseChoosing object) throws DataAccessException {
		CourseChoosing find=findById(object.getId());
		if(find!=null)
			return -1;
		else
			return dao.save(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int update(CourseChoosing object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.update(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int delete(CourseChoosing object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.delete(object);
	}

	@Override
	public List<CourseChoosing> query(int page, int limit) throws DataAccessException {
		
		return dao.query(page, limit);
	}

}

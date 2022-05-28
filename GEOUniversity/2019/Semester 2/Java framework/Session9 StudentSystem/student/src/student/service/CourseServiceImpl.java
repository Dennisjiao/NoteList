package student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import student.dao.CourseDao;
import student.model.Course;

@Service
@Transactional(readOnly=true)
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao dao;
	
	@Override
	public Course findById(String key) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findById(key);
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int save(Course object) throws DataAccessException {
		Course find=findById(object.getCid());
		if(find!=null)
			return -1;
		else
			return dao.save(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int update(Course object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.update(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int delete(Course object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.delete(object);
	}

	@Override
	public List<Course> query(Course object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.query(object);
	}

}

package student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import student.dao.ScoreDao;
import student.model.Course;
import student.model.Score;

@Service
@Transactional(readOnly=true)
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreDao dao;
	
	@Override
	public Score findById(String key) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findById(key);
	}

	@Override
	public List<Score> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Course> findAllCourses() throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.findAllCourses();
	}

	@Override
	public boolean CourseChoosingInfoExist(Score score) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.CourseChoosingInfoExist(score);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int save(Score object) throws DataAccessException {
		Score find=findById(object.getId());
		if(find!=null)
			return -1;
		else
			return dao.save(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int update(Score object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.update(object);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int delete(Score object) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.delete(object);				
	}

	@Override
	public List<Score> query(int page, int limit, Score score) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.query(page, limit, score);
	}

}

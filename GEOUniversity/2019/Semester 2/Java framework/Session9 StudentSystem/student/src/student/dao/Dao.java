package student.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface Dao<PK,T> {

	public T findById(PK key) throws DataAccessException;
	
	public List<T> findAll() throws DataAccessException;
	
	public int save(T object) throws DataAccessException;
	
	public int update(T object) throws DataAccessException;
	
	public int delete(T object) throws DataAccessException;
	
}

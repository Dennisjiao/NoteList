package main.db;

import java.util.List;

import main.domain.Spitter;

/**
 * Repository interface with operations for {@link Spitter} persistence.
 * 
 * @author habuma
 */
public interface SpitterRepository {

	List<Spitter> findAllByJDBC();
	// 查找spitter的所有记录
	List<Spitter> findAll1();
	List<Spitter> findAll2();
	List<Spitter> findAll3();
	List<Spitter> findAll4();
	long count();
	Spitter findOne(long id);	
	Spitter findByUsername(String username,String password);	

	Spitter save(Spitter spitter);
	void insertSpitter(Spitter spitter);
	

}

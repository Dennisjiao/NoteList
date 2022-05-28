package test;

import static org.junit.Assert.*;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import student.configuration.RootConfig;
import student.dao.ScoreDao;
import student.model.Course;
import student.model.Score;
import student.model.StuInfo;

//@Ignore指定忽略此测试方法
//@FixMethodOrder用来指定方法执行顺序，传入一个MethodSorters

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScoreDaoTest {

	@Autowired
	ScoreDao dao;
	
	@Test
	public void test001_save() {		
		Course course=new Course("C01","数学");
		StuInfo stuinfo=new StuInfo();
		stuinfo.setStuid("1001");
		stuinfo.setStuname("aaa");
		java.sql.Date sqlDate=new java.sql.Date(System.currentTimeMillis());
		String id = "1";//UUID.randomUUID().toString().replace("-", "");
		Score score=new Score(id,course,stuinfo,99.0,sqlDate);
		int n=0;
		try{
			n=dao.save(score);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	@Test
	public void test002_findById() {
		Score score=dao.findById("1");
		assertNotNull(score);
	}
	@Test
	public void test003_findAll() {
		List<Score> list=dao.findAll();
		assertEquals(1,list.size());
	}
	
	@Test
	public void test004_update() {
		Score score=dao.findById("1");
		score.setScore(88.0);
		int n=0;
		try{
			n=dao.update(score);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	
	@Test
	public void test005_query() {
		Course course=new Course("C01","数学");
		StuInfo stuinfo=new StuInfo();
		stuinfo.setStuid("1001");
		stuinfo.setStuname("aaa");		
		Score score=new Score(null,course,stuinfo,99.0,null);
		List<Score> list=dao.query(1,5,score);
		assertEquals(1,list.size());
	}
	@Test
	public void test006_delete() {
		Score score=new Score();
		score.setId("1");
		int n=0;
		try{
			n=dao.delete(score);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
}

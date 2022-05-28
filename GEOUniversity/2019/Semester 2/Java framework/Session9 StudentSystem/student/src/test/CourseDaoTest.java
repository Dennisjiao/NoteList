package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import student.configuration.RootConfig;
import student.dao.CourseDao;
import student.model.Course;

//@Ignore指定忽略此测试方法
//@FixMethodOrder用来指定方法执行顺序，传入一个MethodSorters

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseDaoTest {

	@Autowired
	CourseDao dao;
	

	@Test
	public void test001_save() {
		Course course=new Course("C01","数学");
		int n=0;
		try{
			n=dao.save(course);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	@Test
	public void test002_findById() {
		Course course=dao.findById("C01");
		assertNotNull(course);
	}
	@Test
	public void test003_findAll() {
		List<Course> list=dao.findAll();
		assertEquals(1,list.size());
	}
	
	@Test
	public void test004_update() {
		Course course=new Course("C01","英语");
		int n=0;
		try{
			n=dao.update(course);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	
	@Test
	public void test005_query() {
		Course course=new Course("C01","英语");		
		List<Course> list=dao.query(course);
		assertEquals(1,list.size());
	}
	@Test
	public void test006_delete() {
		Course course=new Course("C01","英语");	
		int n=0;
		try{
			n=dao.delete(course);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
}

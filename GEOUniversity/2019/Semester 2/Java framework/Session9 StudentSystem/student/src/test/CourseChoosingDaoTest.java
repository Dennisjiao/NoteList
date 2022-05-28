package test;

import static org.junit.Assert.*;
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
import student.dao.CourseChoosingDao;
import student.model.Course;
import student.model.CourseChoosing;
import student.model.CourseChoosingId;
import student.model.StuInfo;

//@Ignore指定忽略此测试方法
//@FixMethodOrder用来指定方法执行顺序，传入一个MethodSorters

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseChoosingDaoTest {

	@Autowired
	CourseChoosingDao dao;
	
	@Test
	public void test001_save() {
		CourseChoosingId id=new CourseChoosingId("1001","C01");
		Course course=new Course("C01","数学");
		StuInfo stuinfo=new StuInfo();
		stuinfo.setStuid("1001");
		stuinfo.setStuname("aaa");
		java.sql.Date sqlDate=new java.sql.Date(System.currentTimeMillis());
		CourseChoosing cc=new CourseChoosing(id,stuinfo,course,sqlDate);
		int n=0;
		try{
			n=dao.save(cc);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	@Test
	public void test002_findById() {
		CourseChoosingId id=new CourseChoosingId("1001","C01");
		CourseChoosing cc=dao.findById(id);
		assertNotNull(cc);
	}
	@Test
	public void test003_findAll() {
		List<CourseChoosing> list=dao.findAll();
		assertEquals(1,list.size());
	}
	
	@Test
	public void test004_update() {
		CourseChoosingId id=new CourseChoosingId("1001","C01");
		CourseChoosing cc=dao.findById(id);
		cc.setOld_cid(cc.getId());
		CourseChoosingId newid=new CourseChoosingId("1001","C02");
		cc.setId(newid);
		int n=0;
		try{
			n=dao.update(cc);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
	
	@Test
	public void test005_query() {
		List<CourseChoosing> list=dao.query(1,5);
		assertEquals(1,list.size());
	}
	@Test
	public void test006_delete() {
		CourseChoosingId id=new CourseChoosingId("1001","C02");
		CourseChoosing cc=dao.findById(id);
		int n=0;
		try{
			n=dao.delete(cc);
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
		}
		assertEquals(1,n);
	}
}

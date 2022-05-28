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
import student.dao.UserDao;
import student.model.StuInfo;
import student.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ RootConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
	
	@Autowired
	UserDao userdao;
	
	@Test
	public void test001_findById(){
		User user=userdao.findById("admin");
		assertNotNull(user);
	}
	@Test
	public void test002_findAll(){
		List<User> list=userdao.findAll();
		assertEquals(1,list.size());
	}
	@Test
	public void test003_findByStuId(){
		User user=userdao.findByStuId("1001");
		System.out.println(user.toString());
	}
	@Test
	public void test004_Save(){
		User user=new User("aaa","111",1);
		StuInfo stuinfo=new StuInfo("1002","aaa","女",Date.valueOf("2012-12-13"),null,null,null);
		user.setStuinfo(stuinfo);
		int n=0;
		try{
			n=userdao.save(user);
		}
		catch(DataAccessException e){
			e.printStackTrace();
		}
		assertEquals(1,n);
		
	}
	@Test
	public void test005_Update(){
		User user=new User("aaa","222",1);
		StuInfo stuinfo=new StuInfo("1002","bbb","女",Date.valueOf("2012-12-13"),"ddd",null,null);
		user.setStuinfo(stuinfo);
		int n=0;
		try{
			n=userdao.update(user);
		}
		catch(DataAccessException e){
			e.printStackTrace();
		}
		assertEquals(1,n);
		
	}
	@Test
	public void test006_Delete(){
		User user=new User();
		StuInfo stuinfo=new StuInfo();
		stuinfo.setStuid("1002");
		user.setStuinfo(stuinfo);
		int n=0;
		try{
			n=userdao.delete(user);
		}
		catch(DataAccessException e){
			e.printStackTrace();
		}
		assertEquals(1,n);
		
	}
	@Test
	public void test007_login(){
		User user=userdao.login("lhz", "1232");		
		assertNotNull(user);
		
	}
	@Test
	public void test008_query(){
		User user=new User("lhz","123",1);
		StuInfo stuinfo=new StuInfo("1001","aaa","女",Date.valueOf("2020-03-24"),null,null,null);
		user.setStuinfo(stuinfo);
		List<User> list=userdao.query(1, 5, user);
		list.forEach(System.out::println);
		
	}
}

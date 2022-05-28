package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import config.JdbcConfig;
import dao.BookDao;
import dao.OldBookDao;
import model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
@ActiveProfiles("prod")
public class JdbcTest {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private OldBookDao oldBookDao;
	
	/*
	 * 旧访问数据库的方法测试
	 */	
	@Test
	public void OldDaoTest() {
		List<Book> list=oldBookDao.findAllByJDBC();
		list.forEach(System.out::println);
	}
	/*
	 * queryForObject查询一个值（不需要注入参数）
	 */
	@Test
	public void queryForObject1() {		
		System.out.println("queryForObject1:"+bookDao.queryForObject1());
	}
	/*
	 * 查询一个值（使用预处理sql，需要注入参数）
	 */
	@Test
	public void queryForObject2() {		
		System.out.println("queryForObject2:"+bookDao.queryForObject2());
	}
	/*
	 * 查询单行记录，转换成一个对象（固定sql，不需要参数）
	 */
	@Test
	public void queryForObject3() {		
		System.out.println("queryForObject3:"+bookDao.queryForObject3());
	}
	/*
	 * 查询单行记录，转换成一个对象（预处理sql，需要注入参数）
	 */
	@Test
	public void queryForObject4() {		
		System.out.println("queryForObject4:"+bookDao.queryForObject4(2));
	}
	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（固定sql，没参数）
	 */
	@Test
	public void queryForList1() {		
		System.out.println("queryForList1:"+bookDao.queryForList1());
	}
	/*
	 * 查询数据库中一列多行数据，直接将单类型数据存入List中（固定sql，没参数）
	 */
	@Test
	public void queryForList2() {		
		System.out.println("queryForList2:"+bookDao.queryForList2());
	}
	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（预处理sql，需要注入参数）
	 */
	@Test
	public void queryForList3() {		
		System.out.println("queryForList3:"+bookDao.queryForList3("%设计%",20.0));
	}
	/*
	 * 查询多条数据（固定sql，没有参数）
	 */
	@Test
	public void query1() {		
		System.out.println("query1:"+bookDao.query1());
	}
	
	/*
	 * 查询多条数据预处理sql，需要传入参数
	 */
	@Test
	public void query2() {		
		System.out.println("query2:"+bookDao.query2("%设计%",20.0));
	}
	/*
	 * 查询多条数据预处理sql，需要传入参数
	 */
	@Test
	public void query3() {		
		System.out.println("query3:"+bookDao.query3("%设计%",20.0));
	}
	
	/*
	 * update方法用于执行新增
	 */
	@Test
	public void update1() {		
		System.out.println("update1:"+bookDao.update1());
	}
	/*
	 * update方法用于执行新增
	 */
	@Test
	public void update2() {	
		Book book=new Book(4,"test","lhz","aa",new Date(),23.5);
		System.out.println("update2:"+bookDao.update2(book));
		System.out.println("query1:"+bookDao.query1());
	}
	/*
	 * update方法用于执行更新
	 */
	@Test
	public void update3() {	
		Book book=new Book();
		book.setBookId(3);
		book.setBookName("test");
		book.setAuthors("lhz");
		book.setPublisher("aaaa");
		System.out.println("update3:"+bookDao.update3(book));
		System.out.println("query1:"+bookDao.query1());
	}
	
}

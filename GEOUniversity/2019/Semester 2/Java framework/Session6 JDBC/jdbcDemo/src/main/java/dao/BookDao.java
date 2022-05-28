package dao;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookDao {
	
	/*
	 * queryForObject查询一个值（不需要注入参数）
	 */
	public int queryForObject1();

	/*
	 * 查询一个值（使用预处理sql，需要注入参数）
	 */
	public String queryForObject2();
	
	/*
	 * 查询单行记录，转换成一个对象（固定sql，不需要参数）
	 */
	public Book queryForObject3();
	
	/*
	 * 查询单行记录，转换成一个对象（预处理sql，需要注入参数）
	 */
	public Book queryForObject4(int id);
	
	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（固定sql，没参数）
	 */
	public List<Map<String, Object>> queryForList1();
	
	/*
	 * 查询数据库中一列多行数据，直接将单类型数据存入List中（固定sql，没参数）
	 */
	public List<String> queryForList2();
	
	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（预处理sql，需要注入参数）
	 */
	public List<String> queryForList3(String bookname,double price);
	
	/*
	 * 查询多条数据（固定sql，没有参数）
	 */
	public List<Book> query1();
	
	/*
	 * 查询多条数据（预处理sql，需要传入参数）
	 */
	public List<Book> query2(String bookname,double price);
	
	/*
	 * 查询多条数据（预处理sql，需要传入参数）
	 */
	public List<Book> query3(String bookname,double price);
	
	/*
	 * update方法用于执行新增(无参数)
	 */
	public int update1();
	/*
	 * update方法用于执行新增(有参数)
	 */
	public int update2(Book book);
	
	/*
	 * update方法用于执行更新(有参数)
	 */
	public int update3(Book book);
}

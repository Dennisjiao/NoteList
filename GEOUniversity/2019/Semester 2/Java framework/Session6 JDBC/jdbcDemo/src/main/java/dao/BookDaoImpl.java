package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired	
	private JdbcTemplate jdbcTemplate;

	public BookDaoImpl() {

	}

	/*
	 * queryForObject查询一个值（不需要注入参数）
	 */
	@Override
	public int queryForObject1() {
		String sql = "select count(id) from book";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	/*
	 * 查询一个值（使用预处理sql，需要注入参数）
	 */
	@Override
	public String queryForObject2() {
		String sql = "select book_name from book where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { 1 }, String.class);
	}

	/*
	 * 查询单行记录，转换成一个对象（固定sql，不需要参数）
	 */
	@Override
	public Book queryForObject3() {
		String sql = "select id,book_name from book where id=1";
		Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int arg1) throws SQLException {
				Book book = new Book();
				book.setBookId(rs.getInt("id"));
				book.setBookName(rs.getString("book_name"));
				return book;
			}
		});
		return book;
	}

	/*
	 * 查询单行记录，转换成一个对象（预处理sql，需要注入参数）
	 */
	@Override
	public Book queryForObject4(int id) {
		String sql = "select * from book where id=?";
		Book book = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int arg1) throws SQLException {
				Book book = new Book();
				book.setBookId(rs.getInt("id"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthors(rs.getString("authors"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getDouble("price"));
				Date date = new Date(rs.getDate("pub_date").getTime());
				book.setPubDate(date);
				return book;
			}
		});
		return book;
	}

	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（固定sql，没参数）
	 */
	@Override
	public List<Map<String, Object>> queryForList1() {
		String sql = "select book_name from book where id>0";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	/*
	 * 查询数据库中一列多行数据，直接将单类型数据存入List中（固定sql，没参数）
	 */
	@Override
	public List<String> queryForList2() {
		String sql = "select book_name from book where id>0";
		List<String> list = jdbcTemplate.queryForList(sql, String.class);
		return list;
	}

	/*
	 * 查询数据库中一列多行数据，即查询数据库中单列数据存入一个list中（预处理sql，需要注入参数）
	 */
	@Override
	public List<String> queryForList3(String bookname, double price) {
		String sql = "select book_name from book where book_name like ? and price>?";
		List<String> list = jdbcTemplate.queryForList(sql, new Object[] { bookname, price }, String.class);
		return list;
	}

	/*
	 * 记录转换类，用于把查询到的记录转换为Book对象
	 */
	private static final class BookRowMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int i) throws SQLException {
			Book book = new Book();
			book.setBookId(rs.getInt("id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthors(rs.getString("authors"));
			book.setPublisher(rs.getString("publisher"));
			book.setPrice(rs.getDouble("price"));
			Date date = new Date(rs.getDate("pub_date").getTime());
			book.setPubDate(date);
			return book;
		}
	}

	/*
	 * 查询多条数据（固定sql，没有参数）
	 */
	@Override
	public List<Book> query1() {
		String sql = "select * from book";
		List<Book> list = jdbcTemplate.query(sql, new BookRowMapper());
		return list;
	}

	/*
	 * 查询多条数据（预处理sql，需要传入参数）
	 */
	@Override
	public List<Book> query2(String bookname, double price) {
		String sql = "select * from book where book_name like ? and price>?";
		List<Book> list = jdbcTemplate.query(sql, new Object[] { bookname, price },new BookRowMapper());
		return list;
	}
	
	/*
	 * 查询多条数据（预处理sql，需要传入参数）
	 */
	@Override
	public List<Book> query3(String bookname, double price) {
		String sql = "select * from book where book_name like ? and price>?";
		List<Book> list = jdbcTemplate.query(sql,new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bookname);
				ps.setDouble(2, price);				
			}
			
		},new BookRowMapper());
		return list;
	}

	/*
	 * update方法用于执行新增
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@Override
	public int update1() {
		String sql="insert into book values(4,'test','lhz','aa','2020-03-03',23.5)";
		return jdbcTemplate.update(sql);
	}
	/*
	 * update方法用于执行新增(有参数)
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@Override
	public int update2(Book book) {
		String sql="insert into book values(?,?,?,?,?,?)";
		
		return jdbcTemplate.update(sql,new Object[]{
				book.getBookId(),
				book.getBookName(),
				book.getAuthors(),
				book.getPublisher(),
				book.getPubDate(),
				book.getPrice()});
	}
	
	/*
	 * update方法用于执行新增(有参数)
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@Override
	public int update3(Book book) {
		String sql="update book set book_name=?,authors=?,publisher=? where id=?";
		
		return jdbcTemplate.update(sql,	book.getBookName(),book.getAuthors(),book.getPublisher(),book.getBookId());
	}

}

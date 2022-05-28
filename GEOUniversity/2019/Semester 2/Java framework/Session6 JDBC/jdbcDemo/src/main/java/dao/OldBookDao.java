package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Book;
/**
 * 旧访问数据库的方式
 * @author lhzxx
 *
 */
@Repository
public class OldBookDao {
	
	@Autowired
	private DataSource dataSource;
	
	public List<Book> findAllByJDBC() {
		Connection conn=null;
		PreparedStatement stmt=null;
		List<Book> result = new ArrayList<Book>();
		try{
			conn=(Connection) dataSource.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement("select * from book");			
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String bookName = rs.getString("book_name");
				String authors = rs.getString("authors");
				String publisher = rs.getString("publisher");
				Date pubDate = new Date(rs.getDate("pub_date").getTime());
				Double price=rs.getDouble("price");
				Book book = new Book(id, bookName, authors, publisher, pubDate, price);
				result.add(book);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

}

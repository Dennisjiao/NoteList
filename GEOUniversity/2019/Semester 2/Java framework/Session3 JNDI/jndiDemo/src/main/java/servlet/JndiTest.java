package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/test")
public class JndiTest extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			//1、初始化名称查找上下文
			Context initContext = new InitialContext();
			//2、通过JNDI名称找到DataSource,对名称进行定位java:comp/env是必须加的,后面跟的是DataSource名
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
			//3、通过DataSource取得一个连接
			Connection conn = ds.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = String.format("id=%d,book_name=%s,authors=%s", rs.getInt("id"), rs.getString("book_name"),
						rs.getString("authors"));
				System.out.println(str);
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

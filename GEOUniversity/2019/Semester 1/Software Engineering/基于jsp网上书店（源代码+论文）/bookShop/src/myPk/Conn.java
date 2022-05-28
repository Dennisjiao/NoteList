package myPk;
import java.sql.*;

public class Conn {
	private String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
	private String dbUrl="jdbc:odbc:bookShop";
	private String userName="";
	private String userPwd="";
	private Connection conn;
	private Statement st;
	private Pageable rs;
	
	//连接数据库，执行语句，返回得到的记录项－－用于查询
	public void init(String sql) throws Exception
	{
		Class.forName(driverName);
		conn=DriverManager.getConnection(dbUrl, userName,userPwd);
		st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=new PageableResultSet2(st.executeQuery(sql));
	}
	
	//判断记录欺罔是否为空，若为空则调用函数　init(sql)
	public Pageable getRs(String sql) throws Exception
	{
		if(rs==null) 
		{
			init(sql);
		}
		return rs;
	}
	
	//执行 添加、更新、删除，无返回
	public void exeUpd(String sql) throws Exception
	{
		Class.forName(driverName);
		conn=DriverManager.getConnection(dbUrl,userName,userPwd);
		st=conn.createStatement();
		st.executeUpdate(sql);
	}
	
	//关闭　查询　连接
	public void closeRs() throws Exception
	{
		rs.close();
		st.close();
		conn.close();
	}
	
	//关闭　添加、更新、删除　连接
	public void closeNoRs() throws Exception
	{
		st.close();
		conn.close();
	}
}

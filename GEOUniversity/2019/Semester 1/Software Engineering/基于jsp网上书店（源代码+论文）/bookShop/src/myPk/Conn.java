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
	
	//�������ݿ⣬ִ����䣬���صõ��ļ�¼������ڲ�ѯ
	public void init(String sql) throws Exception
	{
		Class.forName(driverName);
		conn=DriverManager.getConnection(dbUrl, userName,userPwd);
		st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=new PageableResultSet2(st.executeQuery(sql));
	}
	
	//�жϼ�¼�����Ƿ�Ϊ�գ���Ϊ������ú�����init(sql)
	public Pageable getRs(String sql) throws Exception
	{
		if(rs==null) 
		{
			init(sql);
		}
		return rs;
	}
	
	//ִ�� ��ӡ����¡�ɾ�����޷���
	public void exeUpd(String sql) throws Exception
	{
		Class.forName(driverName);
		conn=DriverManager.getConnection(dbUrl,userName,userPwd);
		st=conn.createStatement();
		st.executeUpdate(sql);
	}
	
	//�رա���ѯ������
	public void closeRs() throws Exception
	{
		rs.close();
		st.close();
		conn.close();
	}
	
	//�رա���ӡ����¡�ɾ��������
	public void closeNoRs() throws Exception
	{
		st.close();
		conn.close();
	}
}

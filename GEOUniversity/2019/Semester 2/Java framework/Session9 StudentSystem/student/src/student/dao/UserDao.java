package student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import student.model.StuInfo;
import student.model.User;

@Repository
public class UserDao implements Dao<String, User> {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public User findById(String key) throws DataAccessException {
		User user=null;
		try{
			user=jdbc.queryForObject("select * from user left join stuinfo on user.stuid=stuinfo.stuid "
					+ "where username=?", new UserRowMapper(),key);
		}
		catch(EmptyResultDataAccessException e){
			user=null;
		}
		return user;
	}

	@Override
	public List<User> findAll() throws DataAccessException {
		return jdbc.query("select * from user left join stuinfo on user.stuid=stuinfo.stuid where "
				+ "usertype=1 order by username asc",new UserRowMapper());		
	}
	//根据学号查询用户
	public User findByStuId(String stuid) throws DataAccessException{
		User user=null;
		try{
			user=jdbc.queryForObject("select * from user,stuinfo where user.stuid=stuinfo.stuid and "
				+ "stuinfo.stuid=?", new UserRowMapper(),stuid);
		}
		catch(EmptyResultDataAccessException e){
			user=null;
		}
		return user;
	}
	
	@Override
	public int save(User object) throws DataAccessException {
		StuInfo stuinfo=object.getStuinfo();
		if(stuinfo!=null){
			jdbc.update("insert into stuinfo(stuid,stuname,gender,birthday,classname,address) values(?,?,?,?,?,?)",
					stuinfo.getStuid(),stuinfo.getStuname(),stuinfo.getGender(),stuinfo.getBirthday(),
					stuinfo.getClassname(),stuinfo.getAddress());
			return jdbc.update("insert into user(username,password,usertype,stuid) values(?,?,?,?)",
					object.getUsername(),object.getPassword(),object.getUsertype(),stuinfo.getStuid());
		}
		else{
			return jdbc.update("insert into user(username,password,usertype,stuid) values(?,?,?)",
					object.getUsername(),object.getPassword(),object.getUsertype(),null);
		}		
	}

	@Override
	public int update(User object) throws DataAccessException {
		StuInfo stuinfo=object.getStuinfo();
		if(stuinfo!=null){
			jdbc.update("update stuinfo set stuname=?,gender=?,birthday=?,classname=?,address=? where stuid=?",
					stuinfo.getStuname(),stuinfo.getGender(),stuinfo.getBirthday(),
					stuinfo.getClassname(),stuinfo.getAddress(),stuinfo.getStuid());
			return jdbc.update("update user set password=?,usertype=? where stuid=?",
					object.getPassword(),object.getUsertype(),stuinfo.getStuid());
		}
		else{
			return jdbc.update("update user set password=?,usertype=? where username=?",
					object.getPassword(),object.getUsertype(),object.getUsername());
		}	
	}

	@Override
	public int delete(User object) throws DataAccessException {
		jdbc.update("delete from score where stuid=?",object.getStuinfo().getStuid());
		jdbc.update("delete from course_choosing where stuid=?",object.getStuinfo().getStuid());
		jdbc.update("delete from user where stuid=?",object.getStuinfo().getStuid());
		return jdbc.update("delete from stuinfo where stuid=?",object.getStuinfo().getStuid());		
	}
	//用户登录操作
	public User login(String username,String password) throws DataAccessException {
		User user=null;
		try{
			user=jdbc.queryForObject("select * from user left join stuinfo on user.stuid=stuinfo.stuid "
					+ "where username=? and password=?", new UserRowMapper(),username,password);
		}
		catch(EmptyResultDataAccessException e){
			user=null;
		}
		return user;
	}
	//组合sql，type=1代表查询符合条件的总记录数，type=2代表查询所有符合条件的记录
	private String assembleSQL(User user,int type)
	{
		String sql;
		if(type==1)
			sql="select count(user.stuid) from user inner join stuinfo on user.stuid=stuinfo.stuid where 1=1";
		else
			sql="select * from user inner join stuinfo on user.stuid=stuinfo.stuid where 1=1";
		if(user.getUsername() != null && !user.getUsername().equals("")){
			sql += " AND user.username LIKE '%" + user.getUsername() + "%'";
		}
		if(user.getStuinfo().getGender() != null && !user.getStuinfo().getGender().equals("")){
			sql += " AND stuinfo.gender='" + user.getStuinfo().getGender() + "'";
		}
		if(user.getStuinfo().getStuid() != null && !user.getStuinfo().getStuid().equals("")){
			sql += " AND stuinfo.stuid LIKE '%" + user.getStuinfo().getStuid() + "%'";
		}
		if(user.getStuinfo().getStuname() != null && !user.getStuinfo().getStuname().equals("")){
			sql += " AND stuinfo.stuname LIKE '%" + user.getStuinfo().getStuname() + "%'";
		}
		if(user.getStuinfo().getBirthday() != null && !user.getStuinfo().getBirthday().equals("")){
			String myString = DateFormat.getDateInstance().format(user.getStuinfo().getBirthday());
			sql += " AND stuinfo.birthday = '" + myString + "'";
		}
		if(user.getStuinfo().getClassname() != null && !user.getStuinfo().getClassname().equals("")){
			sql += " AND stuinfo.classname LIKE '%" + user.getStuinfo().getClassname() + "%'";
		}
		if(user.getStuinfo().getAddress() != null && !user.getStuinfo().getAddress().equals("")){
			sql += " AND stuinfo.address LIKE '%" + user.getStuinfo().getAddress() + "%'";
		}
		if(type!=1)
			sql+=" order by stuinfo.stuid";
		
		return sql;
	}
	//取总记录数
	public Long getRowCount(User user) throws DataAccessException
	{
		String sql=assembleSQL(user,1);
		Long count=jdbc.queryForObject(sql,Long.class);
		return count;
	}
	
	public List<User> query(int page,int limit,User user) throws DataAccessException
	{
		String sql=assembleSQL(user,2);
		if(limit>0)
		{
			if(page<1)page=1;
			int start=(page-1)*limit;
			sql+=" limit "+start+","+limit;
		}
		return jdbc.query(sql, new UserRowMapper());
	}
	private static class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user=new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setUsertype(rs.getInt("usertype"));
			StuInfo stuinfo=new StuInfo();
			stuinfo.setStuid(rs.getString("user.stuid"));
			stuinfo.setStuname(rs.getString("stuname"));
			stuinfo.setAddress(rs.getString("address"));
			stuinfo.setBirthday(rs.getDate("birthday"));
			stuinfo.setClassname(rs.getString("classname"));
			stuinfo.setGender(rs.getString("gender"));
			user.setStuinfo(stuinfo);			
			return user;
		}
		
	}

}

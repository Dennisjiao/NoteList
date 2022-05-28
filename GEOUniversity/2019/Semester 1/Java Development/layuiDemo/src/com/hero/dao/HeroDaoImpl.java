package com.hero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.hero.pojo.Hero;


public class HeroDaoImpl implements HeroDao {

	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	// 通过构造方法取得数据库连接
	public HeroDaoImpl(Connection connection) { 
		this.conn = connection;  
	}
	@Override
	public boolean add(Hero hero) throws Exception {
		boolean flag = false; // 定义标识
		String sql = "insert into person(name,age,sex,birthday,description) values(?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);// 实例化PrepareStatement对象
		this.pstmt.setString(1,hero.getName());
		this.pstmt.setInt(2,hero.getAge());
		this.pstmt.setString(3, hero.getSex());
		//java.sql.Date sqlDate = java.sql.Date.valueOf(birthday);
		java.sql.Date sqlDate = new java.sql.Date(hero.getBirthday().getTime());
		this.pstmt.setDate(4,sqlDate);
		this.pstmt.setString(5,hero.getDescription());
		
		if (this.pstmt.executeUpdate() > 0) { // 更新记录的行数大于0
			flag = true; // 修改标识
		}
		this.pstmt.close(); // 关闭PreparedStatement操作
		return flag;
	}

	@Override
	public boolean update(Hero hero) throws Exception {
		boolean flag = false; // 定义标识
		String sql = "update person set name=?,age=?,sex=?,birthday=?,description=? where id=?";
		this.pstmt = this.conn.prepareStatement(sql);// 实例化PrepareStatement对象
		this.pstmt.setString(1,hero.getName());
		this.pstmt.setInt(2,hero.getAge());
		this.pstmt.setString(3, hero.getSex());
		//java.sql.Date sqlDate = java.sql.Date.valueOf(birthday);
		java.sql.Date sqlDate = new java.sql.Date(hero.getBirthday().getTime());
		this.pstmt.setDate(4,sqlDate);
		this.pstmt.setString(5,hero.getDescription());
		this.pstmt.setInt(6, hero.getId());
		
		if (this.pstmt.executeUpdate() > 0) { // 更新记录的行数大于0
			flag = true; // 修改标识
		}
		this.pstmt.close(); // 关闭PreparedStatement操作
		return flag;
	}

	@Override
	public boolean delete(Integer heroId) throws Exception {
		boolean flag = false; // 定义标识
		String sql = "delete from person where id=?";		
		this.pstmt = this.conn.prepareStatement(sql);	
		this.pstmt.setInt(1, heroId);
		
		if (this.pstmt.executeUpdate() > 0) { // 更新记录的行数大于0
			flag = true; // 修改标识
		}
		this.pstmt.close(); // 关闭PreparedStatement操作
		return flag;
	}

	@Override
	public List<Hero> findAll() throws Exception {
		List<Hero> list = new ArrayList<Hero>();// 定义集合，接受返回的数据
		String sql = "select * from person ";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();// 执行查询操作
		Hero hero = null;
		while (rs.next()) {
			hero = new Hero();
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setAge(rs.getInt("age"));
			hero.setSex(rs.getString("sex"));
			//Date utilDate=new Date(rs.getDate("birthday").getTime());
			//hero.setBirthday(utilDate);
			hero.setBirthday(rs.getDate("birthday"));
			hero.setDescription(rs.getString("description"));
			hero.setCreate_time(rs.getTimestamp("create_time"));
			list.add(hero); // 向集合中增加product对象
		}
		this.pstmt.close();
		return list; // 返回全部结果
	}

	@Override
	public Hero findByHeroId(Integer heroId) throws Exception {
		String sql = "select * from person where id=? ";		
		this.pstmt = this.conn.prepareStatement(sql);	
		this.pstmt.setInt(1, heroId);
		ResultSet rs = this.pstmt.executeQuery();// 执行查询操作
		Hero hero = null;
		if (rs.next()) {
			hero = new Hero();
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setAge(rs.getInt("age"));
			hero.setSex(rs.getString("sex"));
			hero.setBirthday(rs.getDate("birthday"));
			hero.setDescription(rs.getString("description"));
			hero.setCreate_time(rs.getTimestamp("create_time"));			
		}
		this.pstmt.close();
		return hero;
	}

	@Override
	public List<Hero> findByCriteria(Hero hero) throws Exception {
		List<Hero> list = new ArrayList<Hero>();// 定义集合，接受返回的数据
		String sql = "select * from person where 1=1";
		if(hero.getName()!=null&&!"".equals(hero.getName())){			
			sql += " and name like '%"+hero.getName()+"%'";			
		}
		if(hero.getSex()!=null&&!"".equals(hero.getSex())){			
			sql += " and sex ='"+hero.getSex()+"'";			
		}
		if(hero.getDescription()!=null && !"".equals(hero.getDescription())){			
			sql += " and description like '%"+hero.getDescription()+"%'";			
		}
		if(hero.getAge()!=null ){			
			sql += " and age="+hero.getAge();			
		}
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();// 执行查询操作
		
		while (rs.next()) {
			hero = new Hero();
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setAge(rs.getInt("age"));
			hero.setSex(rs.getString("sex"));
			hero.setBirthday(rs.getDate("birthday"));
			hero.setDescription(rs.getString("description"));
			hero.setCreate_time(rs.getTimestamp("create_time"));
			list.add(hero); // 向集合中增加product对象
		}
		this.pstmt.close();
		return list; // 返回全部结果
	}

}

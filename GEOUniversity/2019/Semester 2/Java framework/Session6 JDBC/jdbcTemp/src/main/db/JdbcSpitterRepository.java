package main.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import main.db.SpitterRepository;
import main.domain.Spitter;

public class JdbcSpitterRepository implements SpitterRepository {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public JdbcSpitterRepository(JdbcTemplate jdbcTemplate,DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource=dataSource;
	}
	
	public List<Spitter> findAllByJDBC() {
		Connection conn=null;
		PreparedStatement stmt=null;
		List<Spitter> result = new ArrayList<Spitter>();
		try{
			conn=(Connection) dataSource.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement("select * from Spitter where username=?");
			stmt.setString(1, "habuma");
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				String email = rs.getString("email");
				boolean updateByEmail = rs.getBoolean("updateByEmail");
				Spitter spitter = new Spitter(id, username, password, fullName, email, updateByEmail);
				result.add(spitter);
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
	/*-----------------------------------查询操作--------------------------------------*/
	// RowMapper接口提供mapRow(ResultSet rs, int
	// rowNum)方法将结果集的每一行转换为一个Map，当然可以转换为其他类。
	public List<Spitter> findAll1() {
		List<Spitter> result= jdbcTemplate.query(
				"select id, username, password, fullname, email, updateByEmail from Spitter order by id",
				new SpitterRowMapper());
		return result;
	}

	private static final class SpitterRowMapper implements RowMapper<Spitter> {
		public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String fullName = rs.getString("fullname");
			String email = rs.getString("email");
			boolean updateByEmail = rs.getBoolean("updateByEmail");
			return new Spitter(id, username, password, fullName, email, updateByEmail);
		}
	}

	// RowCallbackHandler接口也提供方法processRow(ResultSet rs)，能将结果集的行转换为需要的形式。
	public List<Spitter> findAll2() {
		List<Spitter> result = new ArrayList<Spitter>();
		jdbcTemplate.query("select * from Spitter order by id", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				long id = rs.getLong("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				String email = rs.getString("email");
				boolean updateByEmail = rs.getBoolean("updateByEmail");
				Spitter spitter = new Spitter(id, username, password, fullName, email, updateByEmail);
				result.add(spitter);
			}
		});
		return result;
	}

	// ResultSetExtractor使用回调方法extractData(ResultSet rs)提供给用户整个结果集，让用户决定如何处理该结果集。
	public List<Spitter> findAll3() {
		List<Spitter> result = jdbcTemplate.query("select * from Spitter order by id", 
				new ResultSetExtractor<List<Spitter>>() {
			@Override
			public List<Spitter> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Spitter> result = new ArrayList<Spitter>();
				while (rs.next()) {
					long id = rs.getLong("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String fullName = rs.getString("fullname");
					String email = rs.getString("email");
					boolean updateByEmail = rs.getBoolean("updateByEmail");
					Spitter spitter = new Spitter(id, username, password, fullName, email, updateByEmail);
					result.add(spitter);
				}
				return result;
			}
		});
		return result;
	}
	//带参数查询
	public List<Spitter> findAll4() {		
		List<Spitter> result= jdbcTemplate.query(
				"select * from Spitter where username=? and password=?",new RowMapper<Spitter>() {
					public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
						long id = rs.getLong("id");
						String username = rs.getString("username");
						String password = rs.getString("password");
						String fullName = rs.getString("fullname");
						String email = rs.getString("email");
						boolean updateByEmail = rs.getBoolean("updateByEmail");
						return new Spitter(id, username, password, fullName, email, updateByEmail);
					}
				},"habuma","password");
		return result;
	}
	
	public long count() {
		return jdbcTemplate.queryForObject("select count(id) from Spitter", Long.class);
	}
	
	private static final String SELECT_SPITTER = "select id, username, password, fullname, email, updateByEmail from Spitter";
	public Spitter findOne(long id) {
		Spitter spitter=null;
		try{
			spitter= jdbcTemplate.queryForObject(SELECT_SPITTER + " where id=?", new SpitterRowMapper(), id);
		}
		catch (EmptyResultDataAccessException e) {
			spitter=null;
        }
		return spitter;
	}

	public Spitter findByUsername(String username,String password) {
		Spitter spitter=null;
		try{
			spitter= jdbcTemplate.queryForObject(
					"select * from Spitter where username=? and password=?",
					new SpitterRowMapper(), username,password);
		}
		catch (EmptyResultDataAccessException e) {
			spitter=null;
        }
		return spitter;
	}

	public Spitter save(Spitter spitter) {
		Long id = spitter.getId();
		if (id == null) {
			long spitterId = insertSpitterAndReturnId(spitter);
			return new Spitter(spitterId, spitter.getUsername(), spitter.getPassword(), spitter.getFullName(),
					spitter.getEmail(), spitter.isUpdateByEmail());
		} else {
			jdbcTemplate.update(
					"update Spitter set username=?, password=?, fullname=?, email=?, updateByEmail=? where id=?",
					spitter.getUsername(), spitter.getPassword(), spitter.getFullName(), spitter.getEmail(),
					spitter.isUpdateByEmail(), id);
		}
		return spitter;
	}

	/**
	 * Inserts a spitter using SimpleJdbcInsert. Involves no direct SQL and is
	 * able to return the ID of the newly created Spitter.
	 * 
	 * @param spitter
	 *            a Spitter to insert into the databse
	 * @return the ID of the newly inserted Spitter
	 */
	private long insertSpitterAndReturnId(Spitter spitter) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Spitter");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", spitter.getUsername());
		args.put("password", spitter.getPassword());
		args.put("fullname", spitter.getFullName());
		args.put("email", spitter.getEmail());
		args.put("updateByEmail", spitter.isUpdateByEmail());
		long spitterId = jdbcInsert.executeAndReturnKey(args).longValue();
		return spitterId;
	}

	/**
	 * Inserts a spitter using a simple JdbcTemplate update() call. Does not
	 * return the ID of the newly created Spitter.
	 * 
	 * @param spitter
	 *            a Spitter to insert into the database
	 */
	private static final String INSERT_SPITTER = "insert into Spitter (username, password, fullname, email, updateByEmail) values (?, ?, ?, ?, ?)";
	public void insertSpitter(Spitter spitter) {
		jdbcTemplate.update(INSERT_SPITTER, spitter.getUsername(), spitter.getPassword(), spitter.getFullName(),
				spitter.getEmail(), spitter.isUpdateByEmail());
	}
	
	public void deleteSpitter(String username) {
		jdbcTemplate.update("delete from Spitter where username=?", username);
	}
	
}

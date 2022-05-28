package com.myapp;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.myapp.DataSourceConfig;

public class DataSourceConfigTest {

	//@RunWith就是一个运行器
	@RunWith(SpringJUnit4ClassRunner.class)
	//Spring整合JUnit4测试时，使用注解引入多个配置文件
	@ContextConfiguration(classes = DataSourceConfig.class)
	@ActiveProfiles("dev")
	public static class DevDataSourceTest {
		@Autowired
		private DataSource dataSource;

		//@Test:该方法可以不用main方法调用就可以测试出运行结果，是一种测试方法
		@Test
		public void shouldBeEmbeddedDatasource() {
			assertNotNull(dataSource);
			JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			List<String> results = jdbc.query("select id, name from Things", new RowMapper<String>() {
				@Override
				//记录映射，把查询到的每一个行映射为一个“id:name”构成的字符串
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("id") + ":" + rs.getString("name");
				}
			});

			assertEquals(1, results.size());
			assertEquals("1:A", results.get(0));
		}
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = DataSourceConfig.class)
	@ActiveProfiles("prod")
	public static class ProductionDataSourceTest {
		@Autowired
		private DataSource dataSource;

		@Test
		public void shouldBeEmbeddedDatasource() {
			// should be null, because there isn't a datasource configured in JNDI
			assertNull(dataSource);
		}
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:datasource-config.xml")
	@ActiveProfiles("dev")
	public static class DevDataSourceTest_XMLConfig {
		@Autowired
		private DataSource dataSource;

		@Test
		public void shouldBeEmbeddedDatasource() {
			assertNotNull(dataSource);
			JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			List<String> results = jdbc.query("select id, name from Things", new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("id") + ":" + rs.getString("name");
				}
			});

			assertEquals(1, results.size());
			assertEquals("1:A", results.get(0));
		}
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:datasource-config.xml")
	@ActiveProfiles("prod")
	public static class ProductionDataSourceTest_XMLConfig {
		@Autowired(required = false)
		private DataSource dataSource;

		@Test
		public void shouldBeEmbeddedDatasource() {
			// should be null, because there isn't a datasource configured in JNDI
			assertNull(dataSource);
		}
	}

}

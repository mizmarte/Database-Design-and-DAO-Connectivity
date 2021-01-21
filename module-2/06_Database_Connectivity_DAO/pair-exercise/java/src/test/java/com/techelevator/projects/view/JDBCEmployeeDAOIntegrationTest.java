package com.techelevator.projects.view;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

public class JDBCEmployeeDAOIntegrationTest
{
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	private JDBCEmployeeDAO dao;
	
	@BeforeClass
	public static void setUpDataSource()
	{
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException
	{
		dataSource.destroy();
	}

	@Before
	public void setUp()
	{
		dao = new JDBCEmployeeDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

}

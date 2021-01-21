package com.techelevator.projects.view;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCProjectDAOIntegrationTest
{
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	private JDBCProjectDAO dao;
	
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
		dao = new JDBCProjectDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}

	@Test
	public void get_active_should_return_one_project() throws SQLException
	{
		List<Project> results = dao.getAllActiveProjects();
		
		assertEquals("Because there should only be 1 active project", 1, results.size());
		Project project = results.get(0);
		assertEquals("Expecting Neverending Project to return because it is the only active project", "The Never-ending Project", project.getName());
	}
	
	@Test
	public void get_active_should_return_new_project() throws SQLException
	{
		//arrange
		Project testProject = new Project();
		testProject.setName("Test Project");
		testProject.setStartDate(LocalDate.now().minusDays(3));
		testProject.setEndDate(LocalDate.now().plusDays(10));
		// insert project
		jdbcTemplate.update("INSERT INTO project (name, from_date, to_date) VALUES(?,?,?)"
							, testProject.getName()
							, testProject.getStartDate()
							, testProject.getEndDate());
		//act
		List<Project> results = dao.getAllActiveProjects();
		//assert
		assertEquals("Because there should only be 2 active projects", 2, results.size());
		Project project = results.get(0);
		assertEquals("Expecting Neverending Project to return because it is the only active project", "The Never-ending Project", project.getName());
	}
	
	@Test
	public void remove_employee_should_remove_emp_id_from_project_employee_table() throws SQLException
	{
		//arrange
		long projectId=3;
		long employeeId=1;
		//act
		List<Project> results = dao.removeEmployeeFromProject(projectId, employeeId);
		//assert
		
	}
	
}

package com.techelevator.projects.view;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.interfaces.DepartmentDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void change_employee_dept_should_return_employee_id_with_a_different_dept_id()
	{
		//arrange
		//add an employee to the dB 
		//use .setId(pick a number)
		//use insertEmployee(testEmployee) to put in dB (**build method below**)
		//generate list with new employee added
		
		//act
		//use dao function to change employee department
		//generate list with change made
		
		//assert
		//compare both lists - they should not be equal
		
		fail("Not yet implemented");
	}
	
	

	//DTO helpers
	private Employee makeEmployee(Long departmentId, String firstName, String lastName, LocalDate birthDay, char gender, LocalDate hireDate)
	{
		Employee theEmployee = new Employee();
		theEmployee.setDepartmentId(departmentId);
		theEmployee.setFirstName(firstName);
		theEmployee.setLastName(lastName);
		theEmployee.setBirthDay(birthDay);
		theEmployee.setGender(gender);
		theEmployee.setHireDate(hireDate);
		
		return theEmployee;
		
	}

	private void insertEmployee(Employee employee)
	{		
		String query = "INSERT INTO employee " + 
				"( " + 
				"employee_id\r\n" + 
				"        ,department_id\r\n" + 
				"        ,first_name\r\n" + 
				"        ,last_name\r\n" + 
				"        ,birth_date\r\n" + 
				"        ,gender\r\n" + 
				"        ,hire_date )" +  
				"VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(query
							,employee.getId()
							,employee.getDepartmentId()
							, employee.getFirstName()
							, employee.getLastName()
							, employee.getBirthDay()
							, employee.getGender()
							, employee.getHireDate());
				
	}
}

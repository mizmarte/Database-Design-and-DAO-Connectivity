package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.interfaces.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "SELECT employee_id\r\n" + 
						"        ,department_id\r\n" + 
						"        ,first_name\r\n" + 
						"        ,last_name\r\n" + 
						"        ,birth_date\r\n" + 
						"        ,gender\r\n" + 
						"        ,hire_date\r\n" + 
						"\r\n" + 
						"FROM employee;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while(rows.next())
		{
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String query ="SELECT employee_id\r\n" + 
					"        ,department_id\r\n" + 
					"        ,first_name\r\n" + 
					"        ,last_name\r\n" + 
					"        ,birth_date\r\n" + 
					"        ,gender\r\n" + 
					"        ,hire_date\r\n" + 
					"\r\n" + 
					"FROM employee\r\n" + 
					"WHERE first_name = ? AND last_name = ?;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query,firstNameSearch,lastNameSearch);
		
		while(rows.next())
		{
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String query ="SELECT employee_id\r\n" + 
				"        ,department_id\r\n" + 
				"        ,first_name\r\n" + 
				"        ,last_name\r\n" + 
				"        ,birth_date\r\n" + 
				"        ,gender\r\n" + 
				"        ,hire_date\r\n" + 
				"\r\n" + 
				"FROM employee\r\n" + 
				"WHERE department_id = ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query,id);
		
		while(rows.next())
		{
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);
		}
		
		return employees;	
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "SELECT e.employee_id\r\n" + 
					"        ,department_id\r\n" + 
					"        ,first_name\r\n" + 
					"        ,last_name\r\n" + 
					"        ,birth_date\r\n" + 
					"        ,gender\r\n" + 
					"        ,hire_date\r\n" + 
					"FROM employee AS e\r\n" + 
					"LEFT JOIN project_employee AS pe\r\n" + 
					"ON e.employee_id = pe.employee_id\r\n" + 
					"WHERE pe.project_id IS NULL;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while(rows.next())
		{
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);
		}
		
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		return new ArrayList<>();
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
	}
	
	private Employee mapRowToEmployee(SqlRowSet results)
	{
		Employee theEmployee;
		theEmployee = new Employee();
		theEmployee.setId(results.getLong("employee_id"));
		theEmployee.setDepartmentId(results.getLong("department_id"));
		theEmployee.setFirstName(results.getString("first_name"));
		theEmployee.setLastName(results.getString("last_name"));
		theEmployee.setBirthDay(results.getDate("birth_date").toLocalDate());
		//theEmployee.setGender(results.getGender("gender"));
		theEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
		
		return theEmployee;
		
	}

}

package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.interfaces.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() 
	{
		List<Department> departments = new ArrayList<>();
		
		String query = "SELECT department_id\r\n" + 
				"        ,name\r\n" + 
				"FROM department;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while(rows.next())
		{
			Department department = mapRowToDepartment(rows);
			
			departments.add(department);
		}
		
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) 
	{
		List<Department> departments = new ArrayList<>();
		
		String query = "SELECT department_id\r\n" + 
				"        ,name\r\n" + 
				"FROM department\r\n" + 
				"WHERE name ILIKE ?;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, nameSearch);
		
		while(rows.next())
		{
			Department department = mapRowToDepartment(rows);
			
			departments.add(department);
		}
		
		return departments;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) 
	{
		String sqlUpdateDepartment = "UPDATE department\r\n" + 
									"SET name = ?\r\n" + 
									"WHERE department_id =?;";
		jdbcTemplate.update(sqlUpdateDepartment, updatedDepartment.getName(),updatedDepartment.getId());
	}

	@Override
	public Department createDepartment(Department newDepartment) 
	{
		String sqlInsertDepartment = "INSERT INTO department\r\n" + 
				"        (department_id,name)\r\n" + 
				"VALUES (?,?);";
		//nextval() function
		newDepartment.setId(getNextDepartment_Id());
		
		jdbcTemplate.update(sqlInsertDepartment, newDepartment.getId(), newDepartment.getName());
		
		return newDepartment;
	}

	@Override
	public Department getDepartmentById(Long id) 
	{
		Department theDepartment = null;
		String sqlFindDepartmentById = "SELECT department_id\r\n" + 
				"        ,name\r\n" + 
				"FROM department\r\n" + 
				"WHERE department_id =?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindDepartmentById, id);
		
		if(results.next()) {
			theDepartment = mapRowToDepartment(results);
		}
		return theDepartment;
	}
	
	private Department mapRowToDepartment(SqlRowSet results)
	{
		Department theDepartment;
		theDepartment = new Department();
		theDepartment.setId(results.getLong("department_id"));
		theDepartment.setName(results.getString("name"));
		
		return theDepartment;
	}

	private long getNextDepartment_Id()
	{
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_department_id')");
		if(nextIdResult.next())
		{
			return nextIdResult.getLong(1);
		}
		else
		{
			throw new RuntimeException("Something went wrong while getting an id for the new department");
		}
	}
	
}

package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.interfaces.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO
{

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Project> getAllActiveProjects()
	{
		List<Project> projects = new ArrayList<Project>();
		
		String sql = "SELECT project_id " + 
				"        , name " + 
				"        , from_date " + 
				"        , to_date " + 
				"FROM project " + 
				"WHERE NOT(to_date IS NULL AND from_date IS NULL) " + 
				"        AND (to_date IS NULL OR to_date > ?);";
		
		LocalDate today = LocalDate.now();
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, today);
		
		while(rows.next())
		{
			Project project = new Project();
			
			long id = rows.getLong("project_id");
			String name = rows.getString("name");
			
			LocalDate startDate = (rows.getDate("from_date") == null)
									? null
									: rows.getDate("from_date").toLocalDate();
			
			LocalDate endDate = rows.getDate("to_date") == null
								? null
								: rows.getDate("to_date").toLocalDate();
			
			project.setId(id);
			project.setName(name);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			
			projects.add(project);
		}
		
		return projects;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId)
	{
		String deleteQuery = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?;";
		
		jdbcTemplate.update(deleteQuery, projectId, employeeId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId)
	{
		String addEmployee = "INSERT INTO project_employee\r\n" + 
								"        (\r\n" + 
								"                project_id\r\n" + 
								"                ,employee_id\r\n" + 
								"        )\r\n" + 
								"VALUES (?,?);";
		jdbcTemplate.update(addEmployee,projectId,employeeId);
	}

}

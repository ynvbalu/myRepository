package com.naga;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {

	@Override
  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setFirstName(rs.getString("firstName"));
		student.setLastName(rs.getString("lastName"));
		student.setSchool(rs.getString("school"));
		student.setRollNumber(rs.getInt("rollNumber"));
		
		return student;
	}

}

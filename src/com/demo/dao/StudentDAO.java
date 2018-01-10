package com.demo.dao;
import java.util.List;

import com.demo.entities.*;

public interface StudentDAO {

	
	public List<Student> getStudents();
	public Student getStudentInfo(int id);
	public List<Student> getnameMatchStudents(String name);
	public int saveStudent(Student student);
	public int updateStudent(Student student);
}

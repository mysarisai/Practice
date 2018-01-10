package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.Student;

public class StudentDAOImpl implements StudentDAO{

	@Override
	public List<Student> getStudents() {
		
		List<Student>  students = new ArrayList<Student>();
		
		JDBCConnection jdbcConn = new JDBCConnection();
		Connection conn = jdbcConn.getConnection();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from AngularDB.students";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Student st = new Student();
				st.setStudentId(rs.getInt("studentId"));
				st.setStudentName(rs.getString("studentName"));
				st.setGender(rs.getString("gender"));
				st.setCity(rs.getString("city"));
				
				students.add(st);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return students;
	}

	@Override
	public Student getStudentInfo(int id) {
		
		Student st = new Student();
		JDBCConnection jdbcConn = new JDBCConnection();
		Connection conn = jdbcConn.getConnection();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from AngularDB.students where studentId="+id;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				st.setStudentId(rs.getInt("studentId"));
				st.setStudentName(rs.getString("studentName"));
				st.setGender(rs.getString("gender"));
				st.setCity(rs.getString("city"));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return st;
	}

	@Override
	public List<Student> getnameMatchStudents(String name) {
		
		List<Student>  students = new ArrayList<Student>();
		
		JDBCConnection jdbcConn = new JDBCConnection();
		Connection conn = jdbcConn.getConnection();
		
		try{
			PreparedStatement pstmt =  null;
			String sqlString = "select * from AngularDB.students where studentName like ? ";
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, name+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Student st = new Student();
				st.setStudentId(rs.getInt("studentId"));
				st.setStudentName(rs.getString("studentName"));
				st.setGender(rs.getString("gender"));
				st.setCity(rs.getString("city"));
				
				students.add(st);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return students;
		
	}

	@Override
	public int saveStudent(Student student) {
		
		JDBCConnection jdbcConn = new JDBCConnection();
		Connection conn = jdbcConn.getConnection();
		int rowInserted = 0;
		
		try {
			PreparedStatement pstmt =  null;
			String sqlString = "insert into AngularDB.students values (?,?,?,?) ";
			pstmt = conn.prepareStatement(sqlString);
			
			pstmt.setInt(1, student.getStudentId());
			pstmt.setString(2, student.getStudentName());
			pstmt.setString(3, student.getGender());
			pstmt.setString(4, student.getCity());
			
			 rowInserted = pstmt.executeUpdate();
			System.out.println("rowInserted==>" +rowInserted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	   return rowInserted;
	}
	
	@Override
	public int updateStudent(Student student) {
		
		JDBCConnection jdbcConn = new JDBCConnection();
		Connection conn = jdbcConn.getConnection();
		int rowInserted = 0;
		
		try {
			PreparedStatement pstmt =  null;
			String sqlString = "UPDATE AngularDB.Students set studentName=?, gender=?, city=? where studentId=?";
			pstmt = conn.prepareStatement(sqlString);
			
			
			System.out.println("studentName ==>" +student.getStudentName());
			System.out.println("Gender ==>" +student.getGender());
			System.out.println("city ==>" +student.getCity());
			System.out.println("studentI ==>" +student.getStudentId());
			
			pstmt.setString(1, student.getStudentName());
			pstmt.setString(2, student.getGender());
			pstmt.setString(3, student.getCity());
			pstmt.setInt(4, student.getStudentId());
			
			 rowInserted = pstmt.executeUpdate();
			System.out.println("rowInserted==>" +rowInserted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	   return rowInserted;
	}


}

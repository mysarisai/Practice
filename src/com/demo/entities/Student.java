package com.demo.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="student")
public class Student {
	
	private int studentId;
	private String studentName;
	private String gender;
	private String city;
	
	
	@XmlElement(name="studentId")
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@XmlElement(name="studentName")
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@XmlElement(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@XmlElement(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Student(int studentId, String studentName, String gender, String city) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.gender = gender;
		this.city = city;
	}
	public Student() {
		super();
	}
	
	
	

}

package com.demo.entities;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="employee")
public class Employee {
	
	private String employeeId;
	private String employeeName;
	private double employeeSalary;
	private String city;
	
	@XmlElement(name = "employeeId")
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	@XmlElement(name = "employeeName")
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@XmlElement(name = "employeeSalary")
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	@XmlElement(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Employee(String employeeId, String employeeName, double employeeSalary, String city) {
		
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.city = city;
	}
	public Employee() {
		super();
		
	}
	

}

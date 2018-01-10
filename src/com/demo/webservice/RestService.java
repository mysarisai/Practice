package com.demo.webservice;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.demo.dao.StudentDAO;
import com.demo.dao.StudentDAOImpl;
import com.demo.entities.City;
import com.demo.entities.Country;
import com.demo.entities.Employee;
import com.demo.entities.Student;
import com.google.gson.Gson;


@Path("employee")
public class RestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("employeedetails")
	public List<Employee> findAll(){
		List<Employee> result = new ArrayList<Employee>();
		
		result.add(new Employee("E01", "Employee1", 45000, "Bangalore"));
		result.add(new Employee("E02", "Employee2", 55000, "Hyderabad"));
		result.add(new Employee("E03", "Employee3", 65000, "Chennai"));
		result.add(new Employee("E04", "Employee4", 75000, "Pune"));

		
		return result;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("countryDetails")
	public List<Country> getAllCitiesInCountries(){
		List<Country> countries = new ArrayList<Country>();
		
		List<City> citiesObjOne = new ArrayList<City>();
		List<City> citiesObjTwo = new ArrayList<City>();
		List<City> citiesObjThree = new ArrayList<City>();
		
		
		
		citiesObjOne.add(new City(1, "Mumbai", 1));
		citiesObjOne.add(new City(2, "Delhi", 1));
		citiesObjOne.add(new City(3, "Bangalore", 1));
		citiesObjOne.add(new City(4, "Chennai", 1));
		citiesObjOne.add(new City(5, "Hyderabad", 1));
		
		citiesObjTwo.add(new City(1, "NewYork", 2));
		citiesObjTwo.add(new City(2, "Los Angeles", 2));
		citiesObjTwo.add(new City(3, "Chicago", 2));
		citiesObjTwo.add(new City(4, "Hoston", 2));
		citiesObjTwo.add(new City(5, "Philadelphia", 2));
		
		citiesObjThree.add(new City(1, "London", 3));
		citiesObjThree.add(new City(2, "Bringham", 3));
		citiesObjThree.add(new City(3, "Coverty", 3));
		citiesObjThree.add(new City(4, "Liverpool", 3));
		citiesObjThree.add(new City(5, "Manchestor", 3));
		
		
		countries.add(new Country(1, "INDIA", citiesObjOne));
		countries.add(new Country(2, "USA", citiesObjTwo));
		countries.add(new Country(3, "UK", citiesObjThree));
		
		
		return countries;
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getStudents")
	public List<Student> getAllStudents(){
		 List<Student> students = new ArrayList<Student>();
		 
		 students.add(new Student(1, "Raj", "Male", "Bangalore"));
		 students.add(new Student(2, "Vinod", "Male", "Hyderabad"));
		 students.add(new Student(3, "Anusha", "female", "pune"));
		 students.add(new Student(4, "Rekha", "female", "chennai"));
		 students.add(new Student(5, "Surya", "Male", "gurgoan"));
		 
		 return students;

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getStudentsFromDB")
	public String getAllStudentsFromDB() throws InterruptedException{
		//Thread.sleep(2000);
		 List<Student> students = null;
		 String studentDetails = null;
		 
		 StudentDAO studentDAO = new StudentDAOImpl();
		 students = studentDAO.getStudents();
		 
		 Gson gson = new Gson();
		 
		 studentDetails = gson.toJson(students);
		
		 
		 return studentDetails;

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getStudentInfo")
	public String getStudentInfo(@QueryParam("id") int id){
		
		String studentDetails = null;
		
		StudentDAO studentDAO= new StudentDAOImpl();
		Student studentInfo = studentDAO.getStudentInfo(id);
		
		Gson gson = new Gson(); 
		studentDetails = gson.toJson(studentInfo);
		
		
		 return studentDetails;

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getNameMatchStudentsFromDB")
	public String getNameMatchStudentsFromDB(@QueryParam("name") String name){
		System.out.println("name ====>"+name);
		
		String studentDetails = null;
		List<Student> students = null;
		
		StudentDAO studentDAO= new StudentDAOImpl();
		students = studentDAO.getnameMatchStudents(name);
		
		Gson gson = new Gson(); 
		studentDetails = gson.toJson(students);
		
		
		 return studentDetails;

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addStudent")
	public String addStudent(Student student){
		
		StudentDAO studentDAO = new StudentDAOImpl();
		int insertInfo = studentDAO.saveStudent(student);
		Gson gson = new Gson();
		String resp = null;
		
		 if(insertInfo != 0)
			 resp = gson.toJson("success");
		 else
			 resp = gson.toJson("failure");
		
		return resp;
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editStudent")
	public String editStudent(Student student){
		
		StudentDAO studentDAO = new StudentDAOImpl();
		int insertInfo = studentDAO.updateStudent(student);
		Gson gson = new Gson();
		String resp = null;
		
		 if(insertInfo != 0)
			 resp = gson.toJson("success");
		 else
			 resp = gson.toJson("failure");
		
		return resp;
		
	}
	
	
	
	
	/* @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/findall")
	  public Response findAll(){
		  
		  List<Employee> result = new ArrayList<Employee>();
		  result.add(new Employee("E01", "Employee1", 50000, "Bangalore"));
		  result.add(new Employee("E02", "Employee2", 60000, "Hyderabad"));
		  result.add(new Employee("E03", "Employee3", 70000, "Chennai"));
		  result.add(new Employee("E04", "Employee4", 80000, "Mangalore"));
		  
 
		return Response.ok().entity(new GenericEntity<List<Employee>>(result){})
		  	           .header("Access-Control-Allow-Origin", "*")
		  	           .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
		  	           .build();
		  	           
	  }*/
}

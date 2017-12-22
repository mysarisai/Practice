package com.demo.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.demo.entities.Employee;


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

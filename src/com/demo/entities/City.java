package com.demo.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="city")
public class City {
	
	private int id;
	private String name;
	private int countryId;
	
	@XmlElement(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="countryId")
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public City(int id, String name, int countryId) {
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}
	public City() {
		super();
	}
	
	

}

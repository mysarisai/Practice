package com.demo.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="country")
public class Country {
	
	private int id;
	private String name;
	private List<City> cities;
	
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
	@XmlElement(name="cities")
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public Country(int id, String name, List<City> cities) {
		super();
		this.id = id;
		this.name = name;
		this.cities = cities;
	}
	public Country() {
		super();
	}
	
	
	
	
	
	
	

}

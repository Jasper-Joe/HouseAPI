package com.jasper.HouseAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Owner's first name is required")
	private String firstName;
	@NotBlank(message = "Owner's last name is required")
	private String lastName;
	@NotBlank(message = "Street field is required")
	private String street;
	@NotBlank(message = "City field is required")
	private String city;
	@NotBlank(message = "State field is required")
	private String state;
	@NotBlank(message = "Zip field is required")
	private String zip;
	@NotBlank(message = "Property type is required")
	private String propertyType;
	//private String kURL = "http://localhost:8080/api/houses/";
	private String location;
	
	// default constructor
	public House() {
		
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	public String getPropertyType() {
		return propertyType;
	}
	
	public String getState() {
		return state;
	}
	
	
	public String getLocation() {
		return location;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	




	
	

}

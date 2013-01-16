package com.example.jsfdemo.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.resources.DiskManager;

public class Client {
	
	private String firstName = "unknown";
	private String secondName = "unknown";
	private Date dateOfBirth = new Date();
	private String adress="unknown";
	private String zipCode = "";
	private String city="unknown";
	private String pin = "";
	
	
	@Size(min = 2, max = 20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	
	
	@Pattern(regexp = "[0-9]{2}-[0-9]{3}")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Size(min = 2, max = 30)
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	@Size(min = 2, max = 30)
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	
		
	@Past
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Size (min = 2)
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin){
		this.pin=pin;
	}
	 
	
	   
	
	
	
}

package edu.abhi.spring.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Contact {
	
	private int id;
	
	@NotEmpty(message = "name should not be blank.")
	private String name;
	@NotEmpty(message = "email should not be blank.")
	@Email
	private String email;
	@NotEmpty(message = "address should not be blank.")
	private String address;
	@NotEmpty(message = "phone should not be blank.")
	@Pattern(regexp="[0-9]{10}", message="Please enter 10 digit phone number(format:1234567890)")
	private String telephone;
	private String vehicle;
	@NotNull
	@NumberFormat(style = Style.NUMBER) @Min(1)
	private Integer days;

	public Contact() {
	}

	public Contact(String name, String email, String address, String telephone, String vehicle, int days) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.vehicle = vehicle;
		this.days = days;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}


}

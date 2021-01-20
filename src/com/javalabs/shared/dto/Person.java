package com.javalabs.shared.dto;

import javax.ws.rs.QueryParam;

/**
 * Person DTO
 * 
 * @author PATavares
 * @since Jan 2021
 */
import com.javalabs.shared.dto.refdata.PersonStatus;

public class Person implements Model {

	private static final long serialVersionUID = -4044000771030154419L;
	
	@QueryParam("id")
	private Long id;
	@QueryParam("name")
	private String name;
	@QueryParam("surname")
	private String surname;
	@QueryParam("email")
	private String email;
	@QueryParam("phone")
	private String phone;
	@QueryParam("password")
	private String password;
	@QueryParam("status")
	private String status;
	
	public Person() {
		this("", "", "", "");
	}
	
	public Person(String email) {
		this.email = email;
	}

	public Person(String name, String surname, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.status = PersonStatus.ACTIVE.status();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

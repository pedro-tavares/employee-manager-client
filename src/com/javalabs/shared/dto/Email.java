package com.javalabs.shared.dto;

import javax.ws.rs.QueryParam;

/**
 * Email DTO
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class Email implements Model {

	private static final long serialVersionUID = -5095738289505688833L;

	@QueryParam("email")
	private String email;

	public Email() {
		this(null);
	}
	
	public Email(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

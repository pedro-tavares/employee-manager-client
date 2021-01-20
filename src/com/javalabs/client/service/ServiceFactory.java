package com.javalabs.client.service;

import com.google.gwt.core.client.GWT;

/**
 * Service Factory
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class ServiceFactory {

	public static final IPersonService PERSON_SERVICE = GWT.create(IPersonService.class);
	public static final IEmployeeService EMPLOYEE_SERVICE = GWT.create(IEmployeeService.class);

}

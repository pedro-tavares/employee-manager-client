package com.javalabs.client.service;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.javalabs.shared.dto.Employee;

/**
 * Employee Service
 * 
 * @author PATavares
 * @since Jan 2021
 */
@Path("http://localhost:8081/employee")
public interface IEmployeeService extends RestService {

	@GET
	@Path("/all")
	public void all(MethodCallback<List<Employee>> callback);

	@POST
	@Path("/save")
	public void save(@BeanParam Employee site, MethodCallback<Employee> callback);

	@POST
	@Path("/delete")
	public void delete(@BeanParam Employee site, MethodCallback<List<Employee>> callback);
	
}

package com.spring.soap.endpoint;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.spring.soap.model.Employee;
import com.spring.soap.service.EmployeeService;

import allapis.soap.spring.com.AddEmployeeRequest;
import allapis.soap.spring.com.AddEmployeeResponse;
import allapis.soap.spring.com.DeleteEmployeeRequest;
import allapis.soap.spring.com.DeleteEmployeeResponse;
import allapis.soap.spring.com.EmployeeInfo;
import allapis.soap.spring.com.GetEmployeeByIdRequest;
import allapis.soap.spring.com.GetEmployeeResponse;
import allapis.soap.spring.com.ServiceStatus;
import allapis.soap.spring.com.UpdateEmployeeRequest;
import allapis.soap.spring.com.UpdateEmployeeResponse;

@Endpoint
public class EmployeeEndPoint {

	private static final String NAMESPACE_URI = "http://com.spring.soap.allapis";
	
	@Autowired
	private EmployeeService service;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		
		service.addEmployee(employee);
		
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		
		AddEmployeeResponse response = new AddEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		
		EmployeeInfo employeeInfo = new EmployeeInfo();
		BeanUtils.copyProperties(service.getEmployeeById(request.getEmployeeId()), employeeInfo);
		
		GetEmployeeResponse response = new GetEmployeeResponse();
		response.setEmployeeInfo(employeeInfo);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		
		service.updateEmployee(employee);
		
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Updated Successfully");
		
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		
		service.deleteEmployee(request.getEmployeeId());
		
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Deleted Successfully");
		
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		
		return response;
	}
}

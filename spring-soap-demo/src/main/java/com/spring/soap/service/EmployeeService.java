package com.spring.soap.service;

import com.spring.soap.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);
	
	Employee getEmployeeById(long employeeId);
	
	Employee updateEmployee(Employee employee);
	
	Employee deleteEmployee(long employeeId);
}

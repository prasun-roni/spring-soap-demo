package com.spring.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.soap.model.Employee;
import com.spring.soap.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public void addEmployee(Employee employee) {
		
		empRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		
		Employee emp = empRepo.findByEmployeeId(employeeId);
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		Employee emp = this.getEmployeeById(employee.getEmployeeId());
		empRepo.deleteById(emp.getEmployeeId());		
		return empRepo.save(employee);
	}

	@Override
	public Employee deleteEmployee(long employeeId) {

		Employee emp = empRepo.findByEmployeeId(employeeId);
		empRepo.delete(emp);
		return emp;
	}

}

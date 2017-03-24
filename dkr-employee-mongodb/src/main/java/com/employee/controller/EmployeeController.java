package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public Employee create(final @RequestBody Employee nonpersistentEmployee) {		
		return employeeRepository.save(nonpersistentEmployee);
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/{employeeId}")
	public Employee get(final @PathVariable String employeeId) {
		return employeeRepository.findOne(employeeId);
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/")
	public void deleteAll() {
		employeeRepository.deleteAll();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{employeeId}")
	public void deleteOne(final @PathVariable String employeeId) {
		employeeRepository.delete(employeeId);
	}
}

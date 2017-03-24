package com.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}

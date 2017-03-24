package com.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSuccess() {
		Employee nonPersistentEmployee = new Employee("selfemail", "fullname", "mgremail",1,1);
		Employee expected = new Employee("arbitid","selfemail", "fullname", "mgremail",1,1);
		doReturn(expected).when(employeeRepository).save(nonPersistentEmployee);
		Employee actual = employeeController.create(nonPersistentEmployee);
		assertTrue("Return is null", actual != null);
		assertEquals(expected, actual);
		assertNotNull(actual.getId());
	}

}

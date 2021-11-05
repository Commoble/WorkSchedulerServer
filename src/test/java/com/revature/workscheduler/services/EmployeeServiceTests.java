package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.repositories.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class EmployeeServiceTests
{
	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepo repo;

	@Test
	void addEmployee()
	{
		Employee employee = new Employee("Steve Testingperson", "stevet", "parseword", 0);
		int oldID = employee.getEmployeeID();
		Mockito.when(repo.save(employee))
			.thenReturn(new Employee(1, employee.getName(), employee.getUsername(), employee.getPassword(), employee.getStartDate()));
		Employee addedEmployee = service.add(employee);
		int newID = addedEmployee.getEmployeeID();
		Assertions.assertNotEquals(oldID, newID);
		Assertions.assertEquals(employee.getName(), addedEmployee.getName());
		Assertions.assertEquals(employee.getUsername(), addedEmployee.getUsername());
		Assertions.assertEquals(employee.getPassword(), addedEmployee.getPassword());
		Assertions.assertEquals(employee.getStartDate(), addedEmployee.getStartDate());
	}

	@Test
	void getEmployee()
	{
		Employee expectedEmployee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
		int oldID = expectedEmployee.getEmployeeID();
		Optional<Employee> optionalEmployee = Optional.of(expectedEmployee);
		Mockito.when(repo.findById(oldID)).thenReturn(optionalEmployee);
		Employee actualEmployee = this.service.get(oldID);
		int actualID = actualEmployee.getEmployeeID();
		Assertions.assertEquals(oldID, actualID);
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
		Assertions.assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
		Assertions.assertEquals(expectedEmployee.getPassword(), actualEmployee.getPassword());
		Assertions.assertEquals(expectedEmployee.getStartDate(), actualEmployee.getStartDate());
	}

	@Test
	void deleteEmployee()
	{
		int id = 1;
		Employee employee = new Employee("Steve Testingperson", "stevet", "parseword", 0);
		Employee mockEmployee = new Employee(id, employee.getName(), employee.getUsername(), employee.getPassword(), employee.getStartDate());
		Optional<Employee> optionalEmployee = Optional.of(employee);
		Mockito.when(repo.findById(id)).thenReturn(optionalEmployee);
		Mockito.doNothing().when(repo).deleteById(id);

		boolean deleted = this.service.delete(id);
		Assertions.assertTrue(deleted);
	}

	@Test
	void deleteMissingEmployee()
	{
		Employee employee = new Employee("Steve Testingperson", "stevet", "parseword", 0);
		int oldID = employee.getEmployeeID();
		Mockito.when(repo.findById(oldID)).thenReturn(Optional.empty());
		Mockito.doThrow(IllegalArgumentException.class).when(repo).deleteById(oldID);
		boolean deleted = service.delete(oldID);
		Assertions.assertFalse(deleted);
	}
}

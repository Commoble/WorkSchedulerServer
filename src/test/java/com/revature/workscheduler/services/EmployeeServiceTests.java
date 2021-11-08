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
	void getMissingEmployee()
	{
		Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
		int id = employee.getEmployeeID();
		Mockito.when(repo.findById(id)).thenReturn(Optional.empty());
		Assertions.assertNull(this.service.get(id));
	}

	@Test
	void getEmployeesGetsEmployees()
	{
		Assertions.assertNotNull(this.service.getAll());
	}

	@Test
	void updateEmployee()
	{
		Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
		int id = employee.getEmployeeID();
		employee.setName("Updated Name");
		Mockito.when(repo.save(employee)).thenReturn(employee);
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(employee));
		Employee updatedEmployee = this.service.update(employee);
		Assertions.assertEquals(employee.getName(), updatedEmployee.getName());
	}

	@Test
	void updateMissingEmployee()
	{
		Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
		int id = employee.getEmployeeID();
		employee.setName("Updated Name");
		Mockito.when(repo.findById(id)).thenReturn(Optional.empty());
		Mockito.when(repo.save(employee)).thenReturn(employee);
		Assertions.assertNull(this.service.update(employee));
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

	@Test
	void getEmployeeByUsernameGetsEmployee()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void getEmployeeByUsernameDoesntGetMissingEmployee()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void getEmployeeByUsernameDoesntGetEmployeeForNullUsername()
	{
		Employee employee = this.service.getEmployeeByUsername(null);
		Assertions.assertNull(employee);
	}

	@Test
	void employeeThatCanCreateShiftsCanCreateShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void employeeThatCannotCreateShiftsCannotCreateShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void missingEmployeeCannotCreateShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void employeeThatCanAssignShiftsCanAssignShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void employeeThatCannotAssignShiftsCannotAssignShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void missingEmployeeCannotAssignShifts()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void employeeThatCanApproveTimeOffCanApproveTimeOff()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void employeeThatCannotApproveTimeOffCannotApproveTimeOff()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	void missingEmployeeCannotApproveTimeOff()
	{
		Assertions.assertTrue(false); // TODO write test
	}
}

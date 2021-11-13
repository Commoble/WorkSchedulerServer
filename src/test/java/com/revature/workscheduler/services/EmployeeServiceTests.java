package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeRoleJunction;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.repositories.EmployeeRepo;
import com.revature.workscheduler.repositories.EmployeeRoleJunctionRepo;
import com.revature.workscheduler.repositories.EmployeeShiftTypeJunctionRepo;
import com.revature.workscheduler.testutils.ModelGenerators;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class EmployeeServiceTests
{
	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepo repo;

	@MockBean
	private EmployeeRoleJunctionRepo employeeRoleJunctionRepo;

	@MockBean
	private EmployeeShiftTypeJunctionRepo employeeShiftTypeJunctionRepo;

	@MockBean
	private ScheduledShiftService scheduledShiftService;

	@MockBean
	private TimeOffRequestService timeOffRequestService;

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
	void updateNullEmployee()
	{
		Assertions.assertNull(this.service.update(null));
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
	void deleteNullEmployee()
	{
		Mockito.doThrow(IllegalArgumentException.class)
			.when(repo).deleteById(null);
		Assertions.assertFalse(this.service.delete(null));
	}

	@Test
	void getAssignableEmployeesByShiftType()
	{
		int shiftTypeID = 1;
		Employee expectedEmployeeA = ModelGenerators.makeRandomEmployee();
		int employeeIDA = expectedEmployeeA.getEmployeeID();
		expectedEmployeeA.setEmployeeID(employeeIDA);
		ShiftType shiftTypeA = new ShiftType(shiftTypeID, "Test Shift", 0, 1);
		EmployeeShiftTypeJunction junctionA = new EmployeeShiftTypeJunction(1, expectedEmployeeA, shiftTypeA);
		List<EmployeeShiftTypeJunction> junctions = Lists.list(junctionA);
		Mockito.when(this.employeeShiftTypeJunctionRepo.findByShiftTypeShiftTypeID(shiftTypeID))
			.thenReturn(junctions);

		List<Employee> actualEmployees = this.service.getAssignableEmployees(shiftTypeID);
		Assertions.assertEquals(1, actualEmployees.size());
		for (Employee actualEmployee : actualEmployees)
		{
			Assertions.assertEquals(employeeIDA, actualEmployee.getEmployeeID());
		}
	}

	@Test
	void getAssignableEmployeesByShiftTypeAndTime()
	{
		// let's make three employees
		// one's blocked by another scheduled shift
		// one's blocked by a time off request
		// the third is not blocked
		int employeeIDA = 1;
		int employeeIDB = 2;
		int employeeIDC = 3;
		int shiftTypeID = 1;
		Employee expectedEmployeeA = ModelGenerators.makeRandomEmployee();
		expectedEmployeeA.setEmployeeID(employeeIDA);
		Employee expectedEmployeeB = ModelGenerators.makeRandomEmployee();
		expectedEmployeeB.setEmployeeID(employeeIDB);
		Employee expectedEmployeeC = ModelGenerators.makeRandomEmployee();
		expectedEmployeeC.setEmployeeID(employeeIDC);

		ShiftType shiftType = new ShiftType(shiftTypeID, "Test Shift", 0, 1);
		EmployeeShiftTypeJunction junctionA = new EmployeeShiftTypeJunction(1, expectedEmployeeA, shiftType);
		EmployeeShiftTypeJunction junctionB = new EmployeeShiftTypeJunction(2, expectedEmployeeB, shiftType);
		EmployeeShiftTypeJunction junctionC = new EmployeeShiftTypeJunction(3, expectedEmployeeC, shiftType);
		List<EmployeeShiftTypeJunction> junctions = Lists.list(junctionA, junctionB, junctionC);
		ScheduledShift scheduledShiftA = new ScheduledShift(1, shiftType, expectedEmployeeA, 0);
		ScheduledShift scheduledShiftB = new ScheduledShift(2, shiftType, expectedEmployeeB, 1*24*60*60*1000);
		ScheduledShift scheduledShiftC = new ScheduledShift(3, shiftType, expectedEmployeeC, 2*24*60*60*1000);
		TimeOffRequest timeOffRequestA = new TimeOffRequest(1, expectedEmployeeA, 1*24*60*60*1000, 2*24*60*60&1000, true);
		TimeOffRequest timeOffRequestB = new TimeOffRequest(2, expectedEmployeeB, 0, 1, true);
		TimeOffRequest timeOffRequestC = new TimeOffRequest(3, expectedEmployeeC, 1*24*60*60*1000, 2*24*60*60&1000, true);
		Mockito.when(this.employeeShiftTypeJunctionRepo.findByShiftTypeShiftTypeID(shiftTypeID))
			.thenReturn(junctions);
		Mockito.when(this.scheduledShiftService.getScheduledShiftsForEmployee(employeeIDA))
			.thenReturn(Lists.list(scheduledShiftA));
		Mockito.when(this.scheduledShiftService.getScheduledShiftsForEmployee(employeeIDB))
			.thenReturn(Lists.list(scheduledShiftB));
		Mockito.when(this.scheduledShiftService.getScheduledShiftsForEmployee(employeeIDC))
			.thenReturn(Lists.list(scheduledShiftC));
		Mockito.when(this.timeOffRequestService.getNotDeniedRequestsForEmployee(employeeIDA))
			.thenReturn(Lists.list(timeOffRequestA));
		Mockito.when(this.timeOffRequestService.getNotDeniedRequestsForEmployee(employeeIDB))
			.thenReturn(Lists.list(timeOffRequestB));
		Mockito.when(this.timeOffRequestService.getNotDeniedRequestsForEmployee(employeeIDC))
			.thenReturn(Lists.list(timeOffRequestC));
		List<Employee> employees = this.service.getAssignableEmployees(shiftTypeID, 0, 1);
		Assertions.assertEquals(1, employees.size());
		for (Employee employee : employees)
		{
			Assertions.assertEquals(employeeIDC, employee.getEmployeeID());
		}
	}

	@Test
	void getEmployeeByUsernameGetsEmployee()
	{
		int id = 1;
		Employee expectedEmployee = new Employee(id, "Steve Testingperson", "stevet", "parseword", 0);
		String username = "stevet";
		Mockito.when(this.repo.findByUsername(username))
			.thenReturn(expectedEmployee);
		Employee actualEmployee = this.service.getEmployeeByUsername(username);
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
		Assertions.assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
		Assertions.assertEquals(expectedEmployee.getPassword(), actualEmployee.getPassword());
		Assertions.assertEquals(expectedEmployee.getStartDate(), actualEmployee.getStartDate());
	}

	@Test
	void getEmployeeByUsernameDoesntGetMissingEmployee()
	{
		String username = "stevet";
		Mockito.when(this.repo.findByUsername(username))
			.thenReturn(null);
		Employee actualEmployee = this.service.getEmployeeByUsername(username);
		Assertions.assertNull(actualEmployee);
	}

	@Test
	void getEmployeeByUsernameDoesntGetEmployeeForNullUsername()
	{
		Mockito.when(this.repo.findByUsername(null))
			.thenReturn(null);
		Employee actualEmployee = this.service.getEmployeeByUsername(null);
		Assertions.assertNull(actualEmployee);
	}

	@Test
	void isEmployeeManagerIsTrueForManager()
	{
		int employeeID = 1;
		Employee employee = new Employee(employeeID, "Steve Testingperson", "stevet", "parseword", 0);
		Role role = new Role(1, "Manager", true);
		EmployeeRoleJunction junction = new EmployeeRoleJunction(1, employee, role);
		List<EmployeeRoleJunction> junctions = Collections.singletonList(junction);
		Mockito.when(this.employeeRoleJunctionRepo.findByEmployeeEmployeeID(employeeID))
				.thenReturn(junctions);
		boolean isManager = service.isEmployeeManager(employeeID);
		Assertions.assertTrue(isManager);
	}

	@Test
	void isEmployeeManagerIsFalseForNotManager()
	{
		int employeeID = 1;
		Employee mockEmployee = ModelGenerators.makeRandomEmployee();
		mockEmployee.setEmployeeID(employeeID);
		Role mockRole = new Role(1, "Test Role", false);
		EmployeeRoleJunction mockJunction = new EmployeeRoleJunction(1, mockEmployee, mockRole);
		List<EmployeeRoleJunction> mockJunctions = Collections.singletonList(mockJunction);
		Mockito.when(this.employeeRoleJunctionRepo.findByEmployeeEmployeeID(employeeID))
			.thenReturn(mockJunctions);
		boolean isManager = this.service.isEmployeeManager(employeeID);
		Assertions.assertFalse(isManager);
	}

	@Test
	void isEmployeeManagerIsFalseForMissingEmployee()
	{
		int id = 1;
		Mockito.when(this.employeeRoleJunctionRepo.findByEmployeeEmployeeID(id))
			.thenReturn(Collections.emptyList());
		boolean isManager = this.service.isEmployeeManager(id);
		Assertions.assertFalse(isManager);
	}


	@Test
	@WithMockUser(username="stevet", password="password", roles="USER")
	void getLoggedInEmployeeGetsLoggedInEmployee()
	{
		int id = 1;
		String username = "stevet";
		String password = "password";
		Employee mockEmployee = new Employee(id, "Steve Testperson", username, password, 0);
		Mockito.when(this.repo.findByUsername(username))
			.thenReturn(mockEmployee);
		Employee actualEmployee = this.service.getLoggedInEmployee();
		Assertions.assertEquals(id, actualEmployee.getEmployeeID());
	}

	@Test
	@WithMockUser(username="stevet", password="password", roles="USER")
	void getLoggedInEmployeeDoesntGetNotLoggedInEmployee()
	{
		Mockito.when(this.repo.findByUsername("stevet"))
			.thenReturn(null);
		Assertions.assertNull(this.service.getLoggedInEmployee());
	}
}

package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WorkschedulerApplication.class)
@Transactional
public class EmployeeRepoTests
{
	@Autowired
	private EmployeeRepo repo;

	@Test
	@Rollback // because we add employees as part of the test
	void getAllEmployees()
	{
		// add N new employees,
		// then make sure the returned list's size has increased by N

		List<Employee> originalList = new ArrayList<>();
		this.repo.findAll().forEach(originalList::add);
		int originalCount = originalList.size();

		int newEmployeeCount = 3;
		for (int i=0; i<newEmployeeCount; i++)
		{
			this.repo.save(new Employee("Steve", "steve"+i, "steve", 0));
		}

		List<Employee> newList = new ArrayList<>();
		this.repo.findAll().forEach(newList::add);
		int newCount = newList.size();
		int listSizeDifference = newCount - originalCount;

		Assertions.assertEquals(newEmployeeCount, listSizeDifference);
	}

	@Test
	@Rollback
	void addEmployee()
	{
		Employee employee = ModelGenerators.makeRandomEmployee();
		int oldID = employee.getEmployeeID();
		Employee savedEmployee = this.repo.save(employee);
		int newID = savedEmployee.getEmployeeID();
		Assertions.assertNotEquals(oldID, newID);
	}

	@Test
	@Rollback
	void getEmployeeGetsEmployee()
	{
		Employee employee = ModelGenerators.makeRandomEmployee();
		Employee savedEmployee = this.repo.save(employee);
		int id = savedEmployee.getEmployeeID();
		Employee actualEmployee = this.repo.findById(id).get();
		Assertions.assertEquals(id, actualEmployee.getEmployeeID());
		Assertions.assertEquals(savedEmployee.getName(), actualEmployee.getName());
		Assertions.assertEquals(savedEmployee.getUsername(), actualEmployee.getUsername());
		Assertions.assertEquals(savedEmployee.getPassword(), actualEmployee.getPassword());
		Assertions.assertEquals(savedEmployee.getStartDate(), actualEmployee.getStartDate());
	}

	@Test
	@Rollback
	void getMissingEmployeeDoesntGetEmployee()
	{
		// create employee, then delete it, then get it
		Employee employee = ModelGenerators.makeRandomEmployee();
		Employee savedEmployee = this.repo.save(employee);
		int id = savedEmployee.getEmployeeID();
		this.repo.deleteById(id);
		Assertions.assertFalse(this.repo.findById(id).isPresent());
	}

	@Test
	@Rollback
	void updateEmployeeUpdatesEmployee()
	{
		// create employee, then update it
		Employee employee = ModelGenerators.makeRandomEmployee();
		Employee createdEmployee = this.repo.save(employee);
		int id = createdEmployee.getEmployeeID();
		createdEmployee.setName(employee.getName() + "update");
		createdEmployee.setUsername(employee.getUsername() + "update");
		createdEmployee.setPassword(employee.getPassword() + "update");
		createdEmployee.setStartDate(employee.getStartDate()+1);
		this.repo.save(createdEmployee);
		Employee updatedEmployee = this.repo.findById(id).get();
		Assertions.assertEquals(createdEmployee.getEmployeeID(), updatedEmployee.getEmployeeID());
		Assertions.assertEquals(createdEmployee.getName(), updatedEmployee.getName());
		Assertions.assertEquals(createdEmployee.getUsername(), updatedEmployee.getUsername());
		Assertions.assertEquals(createdEmployee.getPassword(), updatedEmployee.getPassword());
		Assertions.assertEquals(createdEmployee.getStartDate(), updatedEmployee.getStartDate());
	}

	@Test
	@Rollback
	void deleteEmployeeDeletesEmployee()
	{
		// create employee, then delete it
		Employee employee = ModelGenerators.makeRandomEmployee();
		Employee createdEmployee = this.repo.save(employee);
		int id = createdEmployee.getEmployeeID();
		this.repo.deleteById(id);
		Assertions.assertFalse(this.repo.findById(id).isPresent());
	}

	@Test
	@Rollback
	void findByUsernameFindsEmployee()
	{
		// create employee
		Employee expectedEmployee = this.repo.save(ModelGenerators.makeRandomEmployee());
		String username = expectedEmployee.getUsername();
		Employee actualEmployee = this.repo.findByUsername(username);
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
		Assertions.assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
		Assertions.assertEquals(expectedEmployee.getPassword(), actualEmployee.getPassword());
		Assertions.assertEquals(expectedEmployee.getStartDate(), actualEmployee.getStartDate());
	}

	@Test
	@Rollback
	void findByUsernameDoesntFindMissingEmployee()
	{
		// create employee, then delete it
		Employee expectedEmployee = this.repo.save(ModelGenerators.makeRandomEmployee());
		int id = expectedEmployee.getEmployeeID();
		String username = expectedEmployee.getUsername();
		this.repo.deleteById(id);
		Employee actualEmployee = this.repo.findByUsername(username);
		Assertions.assertNull(actualEmployee);
	}

	@Test
	@Rollback
	void findByNullUsernameDoesntFindEmployee()
	{
		// username is a nonnull column in the DB,
		// so there will never be an employee with a null username
		Employee actualEmployee = this.repo.findByUsername(null);
		Assertions.assertNull(actualEmployee);
	}
}

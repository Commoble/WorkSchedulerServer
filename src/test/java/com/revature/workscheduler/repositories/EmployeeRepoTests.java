package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
		Employee employee = makeRandomEmployee();
		int oldID = employee.getEmployeeID();
		Employee savedEmployee = this.repo.save(employee);
		int newID = savedEmployee.getEmployeeID();
		Assertions.assertNotEquals(oldID, newID);
	}

	@Test
	@Rollback
	void getEmployeeGetsEmployee()
	{
		Employee employee = makeRandomEmployee();
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
		Employee employee = makeRandomEmployee();
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
		Employee employee = makeRandomEmployee();
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
		Employee employee = makeRandomEmployee();
		Employee createdEmployee = this.repo.save(employee);
		int id = createdEmployee.getEmployeeID();
		this.repo.deleteById(id);
		Assertions.assertFalse(this.repo.findById(id).isPresent());
	}

	@Test
	@Rollback
	void findByUsernameFindsEmployee()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	@Test
	@Rollback
	void findByUsernameDoesntFindMissingEmployee()
	{
		Assertions.assertTrue(false); // TODO write test
	}

	private static Employee makeRandomEmployee()
	{
		long time = System.currentTimeMillis();
		Random rand = new Random(time);
		int nameChars = 10 + rand.nextInt(10);
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<nameChars; i++)
		{
			char nextChar = (char) ('a' + rand.nextInt(26));
			builder.append(nextChar);
		}
		String name = builder.toString();
		return new Employee(name,name,name,time);
	}
}

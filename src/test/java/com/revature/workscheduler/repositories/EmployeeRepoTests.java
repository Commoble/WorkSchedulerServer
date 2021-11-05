package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
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
		Employee employee = new Employee("Steve Testingperson","stevet", "parseword", 0);
		int oldID = employee.getEmployeeID();
		Employee savedEmployee = this.repo.save(employee);
		int newID = savedEmployee.getEmployeeID();
		Assertions.assertNotEquals(oldID, newID);
	}
}

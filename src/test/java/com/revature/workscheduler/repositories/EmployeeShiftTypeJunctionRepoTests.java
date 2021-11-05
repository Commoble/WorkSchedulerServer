package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = WorkschedulerApplication.class)
@Transactional
public class EmployeeShiftTypeJunctionRepoTests
{
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeShiftTypeJunctionRepo repo;

	@Test
	@Rollback
	void findShiftTypesByEmployee()
	{
		// create an employee and a shift type and junction them
		// then ensure this can be found
		List<EmployeeShiftTypeJunction> junctions = repo.findByEmployeeEmployeeID(1);
		Assertions.assertTrue(false); // TODO need shift type repo to finish writing test
	}

	@Test
	@Rollback
	void findEmployeesByShiftType()
	{
		// create an employee and a shift type and junction them
		// then ensure this can be found
		List<EmployeeShiftTypeJunction> junctions = repo.findByShiftTypeShiftTypeID(1);
		Assertions.assertTrue(false); // TODO need shift type repo to finish writing test
	}
}

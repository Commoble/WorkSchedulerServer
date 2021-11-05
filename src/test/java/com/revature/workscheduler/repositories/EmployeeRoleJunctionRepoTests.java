package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.EmployeeRoleJunction;
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
public class EmployeeRoleJunctionRepoTests
{

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeRoleJunctionRepo repo;

	@Test
	@Rollback
	void findShiftTypesByEmployee()
	{
		// create an employee and a role and junction them
		// then ensure this can be found
		List<EmployeeRoleJunction> junctions = repo.findByEmployeeEmployeeID(1);
		Assertions.assertTrue(false); // TODO need role repo to finish writing test
	}
}

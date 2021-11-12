package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeRoleJunction;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.testutils.ModelGenerators;
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
	private RoleRepo roleRepo;

	@Autowired
	private EmployeeRoleJunctionRepo repo;

	@Test
	@Rollback
	void findByEmployeeEmployeeID()
	{
		// create an employee and a role and junction them
		Employee employee = employeeRepo.save(ModelGenerators.makeRandomEmployee());
		int employeeID = employee.getEmployeeID();
		Role role = roleRepo.save(new Role("Test Role", true));
		EmployeeRoleJunction junction = repo.save(new EmployeeRoleJunction(employee, role));
		// then ensure this can be found
		List<EmployeeRoleJunction> junctions = repo.findByEmployeeEmployeeID(employeeID);
		Assertions.assertEquals(1, junctions.size());
		for (EmployeeRoleJunction actualJunction : junctions)
		{
			Assertions.assertEquals(employeeID, actualJunction.getEmployee().getEmployeeID());
		}
	}
}

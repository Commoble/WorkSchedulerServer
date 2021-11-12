package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.models.ShiftType;
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
public class EmployeeShiftTypeJunctionRepoTests
{
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ShiftTypeRepo shiftTypeRepo;

	@Autowired
	private EmployeeShiftTypeJunctionRepo repo;

	@Test
	@Rollback
	void findShiftTypesByEmployee()
	{
		// create an employee and a shift type and junction them
		Employee employee = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		int employeeID = employee.getEmployeeID();
		ShiftType shiftType = this.shiftTypeRepo.save(new ShiftType("Test Shift", 0, 3600000));
		EmployeeShiftTypeJunction junction = this.repo.save(new EmployeeShiftTypeJunction(employee, shiftType));
		// then ensure this can be found
		List<EmployeeShiftTypeJunction> junctions = repo.findByEmployeeEmployeeID(employeeID);
		Assertions.assertEquals(1, junctions.size());
		for (EmployeeShiftTypeJunction actualJunction : junctions)
		{
			Assertions.assertEquals(employeeID, actualJunction.getEmployee().getEmployeeID());
		}
	}

	@Test
	@Rollback
	void findEmployeesByShiftType()
	{
		// create an employee and a shift type and junction them
		Employee employee = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		ShiftType shiftType = this.shiftTypeRepo.save(new ShiftType("Test Shift", 0, 3600000));
		int shiftTypeID = shiftType.getShiftTypeID();
		EmployeeShiftTypeJunction junction = this.repo.save(new EmployeeShiftTypeJunction(employee, shiftType));
		// then ensure this can be found
		List<EmployeeShiftTypeJunction> junctions = repo.findByShiftTypeShiftTypeID(shiftTypeID);
		Assertions.assertEquals(1, junctions.size());
		for (EmployeeShiftTypeJunction actualJunction : junctions)
		{
			Assertions.assertEquals(shiftTypeID, actualJunction.getShiftType().getShiftTypeID());
		}
	}
}

package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.ScheduledShift;
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
public class ScheduledShiftRepoTests
{
	@Autowired
	ScheduledShiftRepo repo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	ShiftTypeRepo shiftTypeRepo;

	@Test
	@Rollback
	void findByDateFindsByDate()
	{
		long expectedDate = 456;
		// make some scheduled shifts and other data
		Employee employee = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		ShiftType shiftType = this.shiftTypeRepo.save(new ShiftType("Test Shift", 0, 1));
		ScheduledShift scheduledShiftA = this.repo.save(new ScheduledShift(shiftType, employee, 123));
		ScheduledShift scheduledShiftB = this.repo.save(new ScheduledShift(shiftType, employee, expectedDate));
		ScheduledShift scheduledShiftC = this.repo.save(new ScheduledShift(shiftType, employee, 789));

		// then try to find one
		List<ScheduledShift> shifts = this.repo.findByDate(expectedDate);
		Assertions.assertTrue(shifts.size() > 0);
		for (ScheduledShift actualShift : shifts)
		{
			Assertions.assertEquals(expectedDate, actualShift.getDate());
		}
	}

	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsByEmployee()
	{
		// make some scheduled shifts and other data
		Employee employeeA = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		int employeeID = employeeA.getEmployeeID();
		Employee employeeB = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		Employee employeeC = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		ShiftType shiftType = this.shiftTypeRepo.save(new ShiftType("Test Shift", 0, 1));
		ScheduledShift scheduledShiftA = this.repo.save(new ScheduledShift(shiftType, employeeA, 123));
		ScheduledShift scheduledShiftB = this.repo.save(new ScheduledShift(shiftType, employeeB, 456));
		ScheduledShift scheduledShiftC = this.repo.save(new ScheduledShift(shiftType, employeeC, 789));

		// then try to find one
		List<ScheduledShift> shifts = this.repo.findByEmployeeEmployeeID(employeeID);
		Assertions.assertEquals(1, shifts.size());
		for (ScheduledShift actualShift : shifts)
		{
			Assertions.assertEquals(employeeID, actualShift.getEmployee().getEmployeeID());
		}
	}
	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsNothingForMissingEmployee()
	{
		// make employee, delete it, get shifts for that employee
		Employee employee = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		int employeeID = employee.getEmployeeID();
		ShiftType shiftType = this.shiftTypeRepo.save(new ShiftType("Test Shift", 0, 1));
		ScheduledShift scheduledShiftA = this.repo.save(new ScheduledShift(shiftType, employee, 123));
		this.employeeRepo.deleteById(employeeID);

		// then try to find one
		List<ScheduledShift> shifts = this.repo.findByEmployeeEmployeeID(employeeID);
		Assertions.assertTrue(shifts.isEmpty());
	}
}

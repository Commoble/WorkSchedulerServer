package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.TimeOffRequest;
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
public class TimeOffRequestRepoTests
{
	@Autowired
	TimeOffRequestRepo repo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsTimeOffRequests()
	{
		// make an employee and two time off requests, one of which is for this employee
		Employee employee = new Employee("Steve Testingperson", "stevet", "password", 0);
		Employee someOtherEmployee = new Employee("Another Employee", "anemp", "password", 0);
		employee = this.employeeRepo.save(employee);
		someOtherEmployee = this.employeeRepo.save(someOtherEmployee);
		int employeeID = employee.getEmployeeID();
		TimeOffRequest request = new TimeOffRequest(employee, 0, 1, true);
		request = this.repo.save(request);
		int requestID = request.getTimeOffRequestID();
		TimeOffRequest anotherRequest = this.repo.save(new TimeOffRequest(someOtherEmployee, 0, 1, true));

		// get requests for one employee
		List<TimeOffRequest> requests = this.repo.findByEmployeeEmployeeID(employeeID);
		Assertions.assertEquals(1, requests.size());
		requests.forEach(actualRequest->Assertions.assertEquals(employeeID, actualRequest.getEmployee().getEmployeeID()));
	}

	@Test
	@Rollback
	void findByEmployeeEmployeeIDDoesntFindRequestsForMissingEmployee()
	{
		// create employee and time off request
		Employee employee = this.employeeRepo.save(ModelGenerators.makeRandomEmployee());
		int employeeID = employee.getEmployeeID();
		TimeOffRequest request = this.repo.save(new TimeOffRequest(employee, 0, 1, true));

		// delete employee
		this.employeeRepo.deleteById(employeeID);

		// get employee's time off requests
		List<TimeOffRequest> requests = this.repo.findByEmployeeEmployeeID(employeeID);

		// list should be empty
		Assertions.assertTrue(requests.isEmpty());
	}

	@Test
	@Rollback
	void findByApprovedNullFindsRequests()
	{
		Assertions.assertTrue(false); // TODO write tests
	}

	@Test
	@Rollback
	void findByEmployeeEmployeeIDAndApprovedNotFalseFindsRequests()
	{
		Assertions.assertTrue(false); // TODO write tests
	}


}

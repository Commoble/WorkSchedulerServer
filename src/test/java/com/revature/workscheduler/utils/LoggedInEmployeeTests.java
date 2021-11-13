package com.revature.workscheduler.utils;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoggedInEmployeeTests
{
	@Test
	void getEmployee()
	{
		Employee employee = ModelGenerators.makeRandomEmployee();
		LoggedInEmployee loggedInEmployee = new LoggedInEmployee(employee);
		Assertions.assertEquals(employee, loggedInEmployee.getEmployee());
	}
}

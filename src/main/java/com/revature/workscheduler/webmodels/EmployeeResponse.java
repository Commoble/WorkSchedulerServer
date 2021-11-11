package com.revature.workscheduler.webmodels;

import com.revature.workscheduler.models.Employee;

public class EmployeeResponse
{
	private int employeeID;
	private String name;
	private long startDate;

	public EmployeeResponse(Employee employee)
	{
		this.employeeID = employee.getEmployeeID();
		this.name = employee.getName();
		this.startDate = startDate;
	}
}

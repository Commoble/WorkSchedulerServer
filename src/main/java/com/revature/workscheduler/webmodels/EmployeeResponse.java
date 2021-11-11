package com.revature.workscheduler.webmodels;

import com.revature.workscheduler.models.Employee;

public class EmployeeResponse
{
	private int id;
	private String name;
	private long startDate;

	public EmployeeResponse(Employee employee)
	{
		this.id = employee.getEmployeeID();
		this.name = employee.getName();
		this.startDate = startDate;
	}
}

package com.revature.workscheduler.webmodels;

public class LoginResponse
{
	private EmployeeResponse employee;
	private boolean isManager;

	public LoginResponse(EmployeeResponse employee, boolean isManager)
	{
		this.employee = employee;
		this.isManager = isManager;
	}

	public EmployeeResponse getEmployee()
	{
		return employee;
	}

	public void setEmployee(EmployeeResponse employee)
	{
		this.employee = employee;
	}

	public boolean isManager()
	{
		return isManager;
	}

	public void setManager(boolean manager)
	{
		isManager = manager;
	}
}

package com.revature.workscheduler.utils;

import com.revature.workscheduler.models.Employee;

import java.util.Optional;

/**
 * Wrapper around a nullable employee to get around issues with spring beans not being null
 */
public class LoggedInEmployee
{
	private Employee employee;

	/**
	 * @param employee Can be null
	 */
	public LoggedInEmployee(Employee employee)
	{
		this.employee = employee;
	}

	/**
	 * @return Nullable employee
	 */
	public Employee getEmployee()
	{
		return employee;
	}
}

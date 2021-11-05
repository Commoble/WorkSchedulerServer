package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.repositories.EmployeeRepo;

import java.util.List;

public interface EmployeeService extends CrudService<Employee, Integer, EmployeeRepo>
{
	/**
	 * Gets all employees that can be assigned to the given shift type
	 * @param shiftTypeID ID of the shift type
	 * @return Employees assignable to shifts of that type
	 */
	public List<Employee> getAssignableEmployees(int shiftTypeID);

	/**
	 * Gets all employees that can be assigned to shifts of the given shift type
	 * that fall between the given times.
	 * @param shiftTypeID ID of the shift type
	 * @param startTime Absolute time in unix epoch milliseconds
	 * @param endTime Absolute time in unix epoch milliseconds
	 * @return Employees assignable to shifts of the given criteria
	 */
	public List<Employee> getAssignableEmployees(int shiftTypeID, long startTime, long endTime);
}

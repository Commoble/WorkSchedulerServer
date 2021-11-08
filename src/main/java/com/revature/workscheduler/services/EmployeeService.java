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

	/**
	 * Returns whether the given employee can create new shift types
	 * @param employeeID ID of an employee
	 * @return True if the employee can create new shift types,
	 * false if they cannot or do not exist
	 */
	public boolean canEmployeeCreateShifts(int employeeID);

	/**
	 * Returns whether the given employee can assign employees to shifts
	 * @param employeeID ID of an employee
	 * @return True if the employee can assign employees to shifts,
	 * false if they cannot or do not exist
	 */
	public boolean canEmployeeAssignShifts(int employeeID);

	/**
	 * Returns whether the given employee can approve time off
	 * @param employeeID ID of an employee
	 * @return True of the employee can approve time off,
	 * false if they cannot or do not exist
	 */
	public boolean canEmployeeApproveTimeOff(int employeeID);
}

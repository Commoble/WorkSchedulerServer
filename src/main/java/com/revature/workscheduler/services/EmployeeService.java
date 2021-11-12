package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.repositories.EmployeeRepo;
import org.springframework.security.core.userdetails.UserDetailsService;

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
	 * Returns the employee with the given username. Each employee has a unique username.
	 * @param username Employee's username.
	 * @return The employee with the given username. Returns null if no matching employee is found.
	 */
	public Employee getEmployeeByUsername(String username);

	/**
	 * Returns whether the given employee is a manager
	 * @param employeeID ID of an employee
	 * @return True if the employee is a manager
	 * false if they cannot or do not exist
	 */
	public boolean isEmployeeManager(int employeeID);

	/**
	 * Returns the employee logged into the current session.
	 * Only usable during an HTTP request.
	 * @return Logged-in employee.
	 * May not return a meaningful value outside of an HTTP request.
	 */
	public Employee getLoggedInEmployee();
}

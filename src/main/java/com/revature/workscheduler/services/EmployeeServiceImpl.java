package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeRoleJunction;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.repositories.EmployeeRepo;
import com.revature.workscheduler.repositories.EmployeeRoleJunctionRepo;
import com.revature.workscheduler.repositories.EmployeeShiftTypeJunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepo repo;

	@Autowired
	private Employee loggedInEmployee;

	@Autowired
	private EmployeeShiftTypeJunctionRepo employeeShiftTypeJunctionRepo;

	@Autowired
	private EmployeeRoleJunctionRepo employeeRoleJunctionRepo;
	
	@Override
	public EmployeeRepo getRepo()
	{
		return this.repo;
	}

	@Override
	public Integer getIDFor(Employee e)
	{
		return e.getEmployeeID();
	}

	@Override
	public List<Employee> getAssignableEmployees(int shiftTypeID)
	{
		return this.employeeShiftTypeJunctionRepo.findByEmployeeEmployeeID(shiftTypeID)
			.stream()
			.map(EmployeeShiftTypeJunction::getEmployee)
			.collect(Collectors.toList());
	}

	@Override
	public List<Employee> getAssignableEmployees(int shiftTypeID, long startTime, long endTime)
	{
		return this.getAll(); // TODO implement filtering
	}

	/**
	 * Returns the employee with the given username. Each employee has a unique username.
	 *
	 * @param username Employee's username.
	 * @return The employee with the given username.
	 * Returns null if no matching employee is found, or if username is null.
	 */
	@Override
	public Employee getEmployeeByUsername(String username)
	{
		if (username == null)
		{
			return null;
		}
		return this.repo.findByUsername(username);
	}

	@Override
	public boolean isEmployeeManager(int employeeID)
	{
		// TODO optimize by letting the database do more of the query
		return this.employeeRoleJunctionRepo.findByEmployeeEmployeeID(employeeID)
			.stream()
			.map(EmployeeRoleJunction::getRoleID)
			.anyMatch(Role::isManager);
	}

	/**
	 * Returns the employee logged into the current session.
	 * Only usable during an HTTP request.
	 *
	 * @return Logged-in employee. Returns null if outside of an HTTP request.
	 */
	@Override
	public Employee getLoggedInEmployee()
	{
		return this.loggedInEmployee;
	}
}

//
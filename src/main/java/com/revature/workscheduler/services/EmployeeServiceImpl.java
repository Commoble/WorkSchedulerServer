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

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	 * Locates the user based on the username. In the actual implementation, the search
	 * may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured. In this case, the <code>UserDetails</code>
	 * object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 *                                   GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Employee employee = this.getEmployeeByUsername(username);
		if (employee == null)
			throw new UsernameNotFoundException("No employee found for username " + username);
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		if (this.isEmployeeManager(employee.getEmployeeID()))
		{
			roles.add("MANAGER");
		}
		return User.builder()
			.username(employee.getUsername())
			.password(employee.getPassword())
			.passwordEncoder(passwordEncoder::encode)
			.roles(roles.toArray(new String[roles.size()]))
			.build();
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
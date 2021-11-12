package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeRoleJunction;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.repositories.EmployeeRepo;
import com.revature.workscheduler.repositories.EmployeeRoleJunctionRepo;
import com.revature.workscheduler.repositories.EmployeeShiftTypeJunctionRepo;
import com.revature.workscheduler.utils.MathUtils;
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
	private ScheduledShiftService scheduledShiftService;

	@Autowired
	private TimeOffRequestService timeOffRequestService;

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
		// TODO optimize by doing more filtering in DB queries
		// get all employees that
		// A) can be assigned to the given shift type, and
		// B) are not already scheduled in the given time, and
		// C) have no time off pending or approved in the given time
		// D) have no recurring unavailabilities in the given time
		return this.employeeShiftTypeJunctionRepo.findByEmployeeEmployeeID(shiftTypeID)
			.stream()
			.map(EmployeeShiftTypeJunction::getEmployee)
			.filter(employee ->{
				int employeeID = employee.getEmployeeID();
				List<ScheduledShift> shifts = this.scheduledShiftService.getScheduledShiftsForEmployee(employeeID);
				// check if employee has any previously scheduled shifts
				for (ScheduledShift shift : shifts)
				{
					ShiftType shiftType = shift.getShiftType();
					long date = shift.getDate(); // start of day in unix epoch millis
					long shiftStartTime = date + shiftType.getStartTime();
					long shiftEndTime = date + shiftType.getEndTime();
					if (MathUtils.doesTimeOverlap(shiftStartTime, shiftEndTime, startTime, endTime))
					{
						return false;
					}
				}

				// check if employee has conflicts in time off
				List<TimeOffRequest> requests = this.timeOffRequestService.getNotDeniedRequestsForEmployee(employeeID);
				for (TimeOffRequest timeOffRequest : requests)
				{
					if (MathUtils.doesTimeOverlap(timeOffRequest.getStartTime(), timeOffRequest.getEndTime(), startTime, endTime))
					{
						return false;
					}
				}

				// TODO check against recurring unavailability

				return true;
			})
			.collect(Collectors.toList());
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
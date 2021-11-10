package com.revature.workscheduler.controllers;

import com.google.gson.Gson;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.List;

@RestController // works better than @Controller
public class EmployeeController
{
	private static final Gson GSON = new Gson();

	@Autowired
	private EmployeeService service;

	/**
	 * Returns information for the employee whose auth credentials are being used.
	 * Can be used to validate that login credentials are correct,
	 * or to get employee's own display name or ID
	 * @return Logged in employee info
	 */
	@Secured("ROLE_USER")
	@GetMapping("/login")
	public Employee getLoggedInEmployee()
	{
		// if we just return getLoggedInEmployee() from here
		// then we get a parsing error
		// (probably because it's a bean disguised as an employee, not an actual employee)
		// we can copy it to a new Employee though
		Employee loggedInEmployee = this.service.getLoggedInEmployee();
		return new Employee(
			loggedInEmployee.getEmployeeID(),
			loggedInEmployee.getName(),
			loggedInEmployee.getUsername(),
			loggedInEmployee.getPassword(),
			loggedInEmployee.getStartDate());
	}

	/**
	 *
	 * @param shiftTypeParam Nullable query parameter, parsed as an int. If present, only employees
	 *                       assignable to that shift type are returned.
	 * @param startTimeParam Nullable query parameter, parsed as a long. If both start time and end
	 *                       time are present, only returns employees able to work between the two times.
	 *                       Time format is unix epoch milliseconds.
	 * @param endTimeParam Nullable query parameter, parsed as a long. If both start time and end
	 *                       time are present, only returns employees able to work between the two times.
	 *                       Time format is unix epoch milliseconds.
	 * @return List of employees
	 */
	@Secured("ROLE_MANAGER")
	@GetMapping("/employees")
	public List<Employee> getEmployees(
		@RequestParam(name="shiftType", required=false) String shiftTypeParam,
		@RequestParam(name="startTime", required=false) String startTimeParam,
		@RequestParam(name="endTime", required=false) String endTimeParam)
	{
		if (shiftTypeParam == null)
		{
			return this.service.getAll();
		}
		int shiftTypeID = ParseUtils.safeParseInt(shiftTypeParam, 0);
		if (startTimeParam == null || endTimeParam == null)
		{
			return this.service.getAssignableEmployees(shiftTypeID);
		}
		long startTime = ParseUtils.safeParseLong(startTimeParam, Long.MIN_VALUE);
		long endTime = ParseUtils.safeParseLong(startTimeParam, Long.MAX_VALUE);
		return this.service.getAssignableEmployees(shiftTypeID, startTime, endTime);
	}

	@Secured("ROLE_MANAGER")
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") String idParam)
	{
		return this.service.get(ParseUtils.safeParseInt(idParam,0));
	}
}

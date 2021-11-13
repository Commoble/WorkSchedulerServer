package com.revature.workscheduler.controllers;

import com.google.gson.Gson;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import com.revature.workscheduler.utils.ParseUtils;
import com.revature.workscheduler.webmodels.EmployeeResponse;
import com.revature.workscheduler.webmodels.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public String getLoggedInEmployee()
	{
		// if we just return getLoggedInEmployee() from here
		// then we get a parsing error
		// (probably because it's a bean disguised as an employee, not an actual employee)
		// we can copy it to a new Employee though
		Employee loggedInEmployee = this.service.getLoggedInEmployee();
		EmployeeResponse employeeResponse = new EmployeeResponse(loggedInEmployee);
		boolean isManager = this.service.isEmployeeManager(loggedInEmployee.getEmployeeID());
		return GSON.toJson(new LoginResponse(employeeResponse, isManager));
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
	public String getEmployees(
		@RequestParam(name="shiftType", required=false) String shiftTypeParam,
		@RequestParam(name="startTime", required=false) String startTimeParam,
		@RequestParam(name="endTime", required=false) String endTimeParam)
	{
		if (shiftTypeParam == null)
		{
			return GSON.toJson(this.service.getAll()
				.stream()
				.map(EmployeeResponse::new)
				.collect(Collectors.toList())
			);
		}
		int shiftTypeID = ParseUtils.safeParseInt(shiftTypeParam, 0);
		if (startTimeParam == null || endTimeParam == null)
		{
			return GSON.toJson(this.service.getAssignableEmployees(shiftTypeID)
				.stream()
				.map(EmployeeResponse::new)
				.collect(Collectors.toList())
			);
		}
		long startTime = ParseUtils.safeParseLong(startTimeParam, Long.MIN_VALUE);
		long endTime = ParseUtils.safeParseLong(endTimeParam, Long.MAX_VALUE);
		return GSON.toJson(this.service.getAssignableEmployees(shiftTypeID, startTime, endTime)
			.stream()
			.map(EmployeeResponse::new)
			.collect(Collectors.toList())
		);
	}

	@Secured("ROLE_MANAGER")
	@GetMapping("/employees/{id}")
	public String getEmployee(@PathVariable("id") String idParam)
	{
		Employee employee = this.service.get(ParseUtils.safeParseInt(idParam,0));
		if (employee == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return GSON.toJson(new EmployeeResponse(employee));
	}
}

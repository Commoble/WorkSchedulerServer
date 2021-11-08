package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@RestController // works better than @Controller
public class EmployeeController
{
	@Autowired
	private EmployeeService service;

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
		return this.service.get(ParseUtils.safeParseInt(idParam,10));
	}

//	@PostMapping(value="/employees", consumes="application/json")
//	public Employee postEmployee(@RequestBody Employee employee)
//	{
//		return null;
//	}
}

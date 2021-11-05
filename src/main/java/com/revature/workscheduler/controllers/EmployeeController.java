package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // works better than @Controller
public class EmployeeController
{
	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		return this.service.getAll();
	}

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

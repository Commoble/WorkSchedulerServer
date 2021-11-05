package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import com.revature.workscheduler.repositories.EmployeeRepo;
import com.revature.workscheduler.repositories.EmployeeRoleJunctionRepo;
import com.revature.workscheduler.repositories.EmployeeShiftTypeJunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
	private EmployeeShiftTypeJunctionRepo employeeShiftTypeJunctionRepo;
	
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
}

package com.revature.workscheduler.services;

import com.revature.workscheduler.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public EmployeeRepo getRepo()
	{
		return this.repo;
	}
}

//
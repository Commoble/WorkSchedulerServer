package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.repositories.EmployeeRepo;

import java.util.List;

public interface EmployeeService extends CrudService<Employee, Integer, EmployeeRepo>
{

}

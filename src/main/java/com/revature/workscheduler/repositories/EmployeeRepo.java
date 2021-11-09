package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer>
{
	/**
	 * Finds an employee with a matching username.
	 * @param username Must not be null
	 * @return Employee with matching username
	 */
	public Employee findByUsername(String username);
}

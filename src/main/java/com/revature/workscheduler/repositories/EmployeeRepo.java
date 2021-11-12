package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer>
{
	/**
	 * Finds an employee with a matching username.
	 * @param username Username of the employee to find (can be null)
	 * @return Employee with matching username.
	 * If no employee with this username is found or username is null, then null is returned.
	 */
	public Employee findByUsername(String username);
}

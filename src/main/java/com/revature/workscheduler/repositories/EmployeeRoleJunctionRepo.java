package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.EmployeeRoleJunction;
import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRoleJunctionRepo extends CrudRepository<EmployeeRoleJunction, Integer>
{

	/**
	 * Retrieves all junctions for the employee of the given ID.
	 * Effectively gets all roles that the employee can be assigned to.
	 * @param employeeID The id of the employee to get junctions for
	 * @return List of employee role junctions
	 */ // spring generates a select query for this
	List<EmployeeRoleJunction> findByEmployeeEmployeeID(int employeeID);
}

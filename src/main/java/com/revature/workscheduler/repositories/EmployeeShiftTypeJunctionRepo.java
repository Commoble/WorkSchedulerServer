package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.EmployeeShiftTypeJunction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeShiftTypeJunctionRepo extends CrudRepository<EmployeeShiftTypeJunction, Integer>
{
	/**
	 * Retrieves all junctions for the employee of the given ID.
	 * Effectively gets all shift types that the employee can be assigned to.
	 * @param employeeID The id of the employee to get junctions for
	 * @return List of employee shift type junctions
	 */ // spring generates a select query for this
	List<EmployeeShiftTypeJunction> findByEmployeeEmployeeID(int employeeID);

	/**
	 * Retrieves all junctions for the given shift type.
	 * Effectively gets all employees that can be assigned to the given shift type.
	 * @param shiftTypeID
	 * @return List of employee shift type junctions
	 */
	List<EmployeeShiftTypeJunction> findByShiftTypeShiftTypeID(int shiftTypeID);
}

package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer>
{

}

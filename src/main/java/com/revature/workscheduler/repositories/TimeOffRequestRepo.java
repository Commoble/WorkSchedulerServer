package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.TimeOffRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeOffRequestRepo extends CrudRepository<TimeOffRequest, Integer>
{
    public List<TimeOffRequest> findByEmployeeEmployeeID(int ID);
    public List<TimeOffRequest> findByApprovedNull();

    /**
     * @param employeeID
     * @return Gets all time off requests for an employee that are not denied (either pending or approved).
     * Returns an empty list if employee not found.
     */
    public List<TimeOffRequest> findByEmployeeEmployeeIDAndApprovedNotFalse(int employeeID);
}

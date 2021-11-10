package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.RecurringUnavailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringUnavailabilityRepo extends CrudRepository<RecurringUnavailability,Integer> {

    //////////////////////////////////////// CREATE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability createRecurringUnavailability(RecurringUnavailability newRecurringUnavailability);
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// READ OPERATIONS ////////////////////////////////////////
    RecurringUnavailability getRecurringUnavailabilityByRecurringUnavailabilityId(int recurring_unavailability_id);
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(int employee_id);
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(int weekday);
    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime);
    RecurringUnavailability getRecurringUnavailabilityById(int recurringUnavailabilityID);
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// UPDATE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability updateRecurringUnavailability(int recurring_unavailability_id);
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// DELETE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability deleteRecurringUnavailability(int recurring_unavailability_id);
    //////////////////////////////////////////////////////////////////////////////////////////////////

}

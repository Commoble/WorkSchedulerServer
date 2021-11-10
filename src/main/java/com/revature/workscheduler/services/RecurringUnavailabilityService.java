package com.revature.workscheduler.services;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;

import java.util.List;


public interface RecurringUnavailabilityService extends CrudService<RecurringUnavailability, Integer, RecurringUnavailabilityRepo>
{

    //////////////////////////////////////// CREATE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability createRecurringUnavailability(RecurringUnavailability newRecurringUnavailability);
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// READ OPERATIONS ////////////////////////////////////////
    RecurringUnavailability getRecurringUnavailabilityByRecurringUnavailabilityId(int recurring_unavailability_id);
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(int employee_id);
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(int weekday);
//    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime);
    RecurringUnavailability getRecurringUnavailabilityById(int recurringUnavailabilityID);
    //////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////// UPDATE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability updateRecurringUnavailability(int recurring_unavailability_id);
    //////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// DELETE OPERATIONS ////////////////////////////////////////
    RecurringUnavailability deleteRecurringUnavailability(int recurring_unavailability_id);
    //////////////////////////////////////////////////////////////////////////////////////////////////
}

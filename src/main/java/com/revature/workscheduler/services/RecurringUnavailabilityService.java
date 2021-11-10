package com.revature.workscheduler.services;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;

import java.util.List;


public interface RecurringUnavailabilityService extends CrudService<RecurringUnavailability, Integer, RecurringUnavailabilityRepo>
{

    //////////////////////////////////////// READ OPERATIONS ////////////////////////////////////////
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(int employee_id);
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(int weekday);
    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime);
    //////////////////////////////////////////////////////////////////////////////////////////////////
}

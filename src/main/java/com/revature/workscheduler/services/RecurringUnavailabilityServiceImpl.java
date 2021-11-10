package com.revature.workscheduler.services;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringUnavailabilityServiceImpl implements RecurringUnavailabilityService
{
    @Autowired
    private RecurringUnavailabilityRepo rur;

    @Override
    public RecurringUnavailabilityRepo getRepo()
    {
        return this.rur;
    }

    /**
     * @param value A record to get the value of. Must not be null.
     * @return The ID of the value
     */
    @Override
    public Integer getIDFor(RecurringUnavailability value)
    {
        return value.getRecurringUnavailabilityID();
    }


    @Override
    public RecurringUnavailability createRecurringUnavailability(RecurringUnavailability newRecurringUnavailability) {
        return rur.createRecurringUnavailability(newRecurringUnavailability);
    }

    @Override
    public RecurringUnavailability getRecurringUnavailabilityByRecurringUnavailabilityId(int recurring_unavailability_id) {
        return rur.getRecurringUnavailabilityByRecurringUnavailabilityId(recurring_unavailability_id);
    }

    @Override
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(int employee_id) {
        return rur.getRecurringUnavailabilityByEmployee(employee_id);
    }

    @Override
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(int weekday) {
        return rur.getRecurringUnavailabilityByWeekday(weekday);
    }

//    @Override
//    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime) {
//
//        return rur.getRecurringUnavailabilityByTime(startTime, endTime);
//    }

    @Override
    public RecurringUnavailability getRecurringUnavailabilityById(int recurringUnavailabilityID){
        return rur.getRecurringUnavailabilityById(recurringUnavailabilityID);
    }

    @Override
    public RecurringUnavailability updateRecurringUnavailability(int recurring_unavailability_id) {
        return rur.updateRecurringUnavailability(recurring_unavailability_id);
    }

    @Override
    public RecurringUnavailability deleteRecurringUnavailability(int recurring_unavailability_id) {
        return rur.deleteRecurringUnavailability(recurring_unavailability_id);
    }
}


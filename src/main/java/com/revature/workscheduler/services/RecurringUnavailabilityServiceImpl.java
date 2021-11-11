package com.revature.workscheduler.services;
import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
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
    public Integer getIDFor(RecurringUnavailability value) {return value.getRecurringUnavailabilityID();}

    @Override
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(int employee_id) {return rur.findByEmployeeEmployeeID(employee_id);}

    @Override
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(int weekday) {return rur.findByWeekday(weekday);}

    @Override
    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime) {
        return Collections.emptyList(); // TODO implement
    }
}


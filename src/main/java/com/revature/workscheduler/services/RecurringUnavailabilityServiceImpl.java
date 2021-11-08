package com.revature.workscheduler.services;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}


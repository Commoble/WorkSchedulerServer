package com.revature.workscheduler.services;

import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecurringUnavailabilityServiceImpl implements RecurringUnavailabilityService
{
    @Autowired
    private RecurringUnavailabilityRepo rur;

    @Override
    public RecurringUnavailabilityRepo getRecurringUnavailabilityRepo()
    {
        return this.rur;
    }
}


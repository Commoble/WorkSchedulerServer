package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.services.RecurringUnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecurringUnavailabilityController {

    @Autowired
    private RecurringUnavailabilityService service;

    @PostMapping(value="/ru", consumes="application/json",produces="application/json")
    public RecurringUnavailability createRecurringUnavailability(@RequestBody RecurringUnavailability newRecurringUnavailability)
    {return this.service.createRecurringUnavailability(newRecurringUnavailability);}

    @GetMapping("/ru/{id}")
    public RecurringUnavailability getRecurringUnavailabilityByRecurringUnavailabilityId(@PathVariable("id") String id)
    {return this.service.getRecurringUnavailabilityByRecurringUnavailabilityId(Integer.parseInt(id));}

    @GetMapping("/ru/{employee_id}")
    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(@PathVariable("employee_id") String employee_id)
    {return this.service.getRecurringUnavailabilityByEmployee(Integer.parseInt(employee_id));}

    @GetMapping("/ru/{weekday}")
    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(@PathVariable("weekday") String weekday)
    {return this.service.getRecurringUnavailabilityByWeekday(Integer.parseInt(weekday));}

//    @GetMapping("/ru/{time}")
//    {return this.service.getRecurringUnavailabilityByTime(long startTime, long endTime);}

    @GetMapping("/ru/{recurringUnavailabilityID}")
    public RecurringUnavailability getRecurringUnavailabilityById(@PathVariable("recurringUnavailabilityID") String recurringUnavailabilityID)
    {return this.service.getRecurringUnavailabilityById(Integer.parseInt(recurringUnavailabilityID));}

    @PutMapping("/ru/{id}")
    public RecurringUnavailability updateRecurringUnavailability(@PathVariable("recurring_unavailability_id") String recurring_unavailability_id)
    {return this.service.updateRecurringUnavailability(Integer.parseInt(recurring_unavailability_id));}

    @DeleteMapping("/ru/{id}")
    public RecurringUnavailability deleteRecurringUnavailability(@PathVariable("recurring_unavailability_id") String recurring_unavailability_id)
    {return this.service.deleteRecurringUnavailability(Integer.parseInt(recurring_unavailability_id));}


}


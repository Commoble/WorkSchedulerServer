package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.services.RecurringUnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class RecurringUnavailabilityController {

    @Autowired
    private RecurringUnavailabilityService service;

    @PostMapping(value="/ru", consumes="application/json",produces="application/json")
    public RecurringUnavailability createRecurringUnavailability(@RequestBody RecurringUnavailability newRecurringUnavailability)
    {return this.service.add(newRecurringUnavailability);}

    @GetMapping("/ru/{id}")
    public RecurringUnavailability getRecurringUnavailabilityByRecurringUnavailabilityId(@PathVariable("id") String id)
    {return this.service.get(Integer.parseInt(id));}

//    @GetMapping("/ru/{employee_id}")
//    public List<RecurringUnavailability> getRecurringUnavailabilityByEmployee(@PathVariable("employee_id") String employee_id)
//    {return this.service.getRecurringUnavailabilityByEmployee(Integer.parseInt(employee_id));}
//
//    @GetMapping("/ru/{weekday}")
//    public List<RecurringUnavailability> getRecurringUnavailabilityByWeekday(@PathVariable("weekday") String weekday)
//    {return this.service.getRecurringUnavailabilityByWeekday(Integer.parseInt(weekday));}

//    @GetMapping("/ru/{time}")
//    {return this.service.getRecurringUnavailabilityByTime(long startTime, long endTime);}

//    @PutMapping("/ru/{id}")
//    public RecurringUnavailability updateRecurringUnavailability(@PathVariable("recurring_unavailability_id") String recurring_unavailability_id)
//    {return this.service.update(Integer.parseInt(recurring_unavailability_id));}

    @DeleteMapping("/ru/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecurringUnavailability(@PathVariable("recurring_unavailability_id") String recurring_unavailability_id)
    {
        boolean deleted = this.service.delete(Integer.parseInt(recurring_unavailability_id));
        if (!deleted)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}


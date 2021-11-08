package com.revature.workscheduler.controllers;

import com.revature.workscheduler.services.RecurringUnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecurringUnavailabilityController {
    @Autowired
    private RecurringUnavailabilityService service;

    @GetMapping("/setRecurringUnavailability")

}

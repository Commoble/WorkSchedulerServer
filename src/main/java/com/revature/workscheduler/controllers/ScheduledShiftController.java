package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.services.ScheduledShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduledShiftController {
    @Autowired
    private ScheduledShiftService sss;

    @GetMapping("/schedule/{id}")
    public ScheduledShift getScheduledShift(@PathVariable("id") String id) {
        return sss.get(Integer.parseInt(id));
    }

    @GetMapping("/schedules")
    public List<ScheduledShift> getAllScheduledShift() {
        return sss.getAll();
    }

    @PostMapping(value = "/schedule", consumes = "application/json", produces = "application/json")
    public ScheduledShift addScheduledShift(@RequestBody ScheduledShift a) {
        return sss.add(a);
    }

    @PutMapping(value = "schedule/{id}", consumes = "application/json", produces = "application/json")
    public ScheduledShift updateScheduledShift(@PathVariable("id") String id, @RequestBody ScheduledShift change) {
        change.setScheduledShiftID(Integer.parseInt(id));
        return sss.update(change);
    }








}

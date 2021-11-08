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

    @DeleteMapping("schedule/{id}")
    public boolean deleteScheduledShift(@PathVariable("id") int id) {
        return sss.delete(id);
    }

    @PostMapping(value = "/schedule", consumes = "application/json", produces = "application/json")
    public ScheduledShift addScheduledShift(@RequestBody ScheduledShift a) {
        return sss.add(a);
    }

    @GetMapping("/schedules/search")
    public List<ScheduledShift> searchSchedule(@RequestParam long date) {
        return sss.getScheduleShift(date);
    }






}

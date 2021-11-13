package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.services.ScheduledShiftService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduledShiftController {
    @Autowired
    private ScheduledShiftService sss;

    @Secured("ROLE_USER")
    @GetMapping("/schedule/{id}")
    public ScheduledShift getScheduledShift(@PathVariable("id") String id) {
        return sss.get(Integer.parseInt(id));
    }

    @Secured("ROLE_USER")
    @GetMapping("/schedule")
    public List<ScheduledShift> getAllScheduledShift() {
        return sss.getAll();
    }

    @Secured("ROLE_MANAGER")
    @PostMapping(value = "/schedule", consumes = "application/json", produces = "application/json")
    public ScheduledShift addScheduledShift(@RequestBody ScheduledShift a) {
        return sss.add(a);
    }

//    @PutMapping(value = "schedule/{id}", consumes = "application/json", produces = "application/json")
//    public ScheduledShift updateScheduledShift(@PathVariable("id") String id, @RequestBody ScheduledShift change) {
//        change.setScheduledShiftID(ParseUtils.safeParseInt(id, 0));
//        return sss.update(change);
//    }







}

package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.services.TimeOffRequestService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeOffRequestController
{
    @Autowired
    private TimeOffRequestService tors;

    @GetMapping("/timeoff/{id}")
    public List<TimeOffRequest> getRequestsByEmpID(@PathVariable("id") String id)
    {
        return tors.getByEmployeeID(ParseUtils.safeParseInt(id, 0));
    }

    @GetMapping("/timeoff")
    public List<TimeOffRequest> getPendingRequests()
    {
        return tors.getPendingRequests();
    }

    @PostMapping(value = "/timeoff", consumes = "application/json")
    public TimeOffRequest addRequest(@RequestBody TimeOffRequest req)
    {
        return tors.add(req);
    }

    @PatchMapping(value = "/timeoff/{id}")
    public TimeOffRequest patchApproval(@PathVariable("id") String id, @RequestParam(name="approved", required = true) boolean approval)
    {

        TimeOffRequest tor = tors.get(ParseUtils.safeParseInt(id, 0));
        tor.setApproved(approval);
        return tors.update(tor);
    }
}

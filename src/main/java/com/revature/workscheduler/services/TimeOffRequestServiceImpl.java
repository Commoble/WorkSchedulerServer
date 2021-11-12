package com.revature.workscheduler.services;

import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.repositories.TimeOffRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOffRequestServiceImpl implements TimeOffRequestService
{
    @Autowired
    private TimeOffRequestRepo torr;

    @Override
    public TimeOffRequestRepo getRepo()
    {
        return torr;
    }

    @Override
    public Integer getIDFor(TimeOffRequest value)
    {
        return value.getTimeOffRequestID();
    }

    @Override
    public List<TimeOffRequest> getByEmployeeID(int id)
    {
        return torr.findByEmployeeEmployeeID(id);
    }

    @Override
    public List<TimeOffRequest> getPendingRequests()
    {
        return torr.findByApprovedNull();
    }

    @Override
    public List<TimeOffRequest> getNotDeniedRequestsForEmployee(int employeeID)
    {
        return torr.findByEmployeeEmployeeIDAndApprovedNotFalse(employeeID);
    }
}

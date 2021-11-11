package com.revature.workscheduler.services;

import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.repositories.TimeOffRequestRepo;

import java.util.List;

public interface TimeOffRequestService extends CrudService<TimeOffRequest, Integer, TimeOffRequestRepo>
{
    public List<TimeOffRequest> getByEmployeeID(int id);
    public List<TimeOffRequest> getPendingRequests();
}

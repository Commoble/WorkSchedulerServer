package com.revature.workscheduler.services;

import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledShiftServiceImpl implements ScheduledShiftService {

    @Autowired
    ScheduledShiftRepo ssr;


    public ScheduledShiftRepo getRepo() {
        return this.ssr;
    }

    public Integer getIDFor(ScheduledShift value) {
        return value.getScheduledShiftID();
    }

    @Override
    public List<ScheduledShift> getScheduledShiftsForEmployee(int employeeID)
    {
        return this.ssr.findByEmployeeEmployeeID(employeeID);
    }
}

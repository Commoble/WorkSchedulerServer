package com.revature.workscheduler.services;

import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;

import java.util.List;

public interface ScheduledShiftService extends CrudService <ScheduledShift, Integer, ScheduledShiftRepo>
{
//    public ScheduledShift getScheduleByDate(int date);

    public List<ScheduledShift> getScheduleShift(long date);
    /**
     * Get schedule posted on a specific date
     * @param date for reference of day
     * @return All shifts posted on that date
     */

//    public ScheduledShift getScheduleByDate(int date, int ScheduledShiftID);
//    /**
//     * Get schedule posted for a specific shift on a specific date
//     * @param dates a specific day
//     * @param ScheduledShiftID to narrow down a specific shift
//     * @return the post schedule per shift for the date
//     */



}

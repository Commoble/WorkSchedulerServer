package com.revature.workscheduler.services;

import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;

import java.util.List;

public interface ScheduledShiftService extends CrudService <ScheduledShift, Integer, ScheduledShiftRepo>

{

//    public ScheduledShift addScheduledShift(ScheduledShift a);
//    public ScheduledShift getScheduledShift(int id);
//    public List<ScheduledShift> getAllScheduledShift();
//    public ScheduledShift updateScheduledShift(ScheduledShift change);

	/**
	 * Gets all the shifts scheduled for this employee
	 * @param employeeID
	 * @return scheduled shifts; returns empty list if employee not found
	 */
	public List<ScheduledShift> getScheduledShiftsForEmployee(int employeeID);

}

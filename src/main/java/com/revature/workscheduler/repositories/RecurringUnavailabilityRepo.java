package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.RecurringUnavailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringUnavailabilityRepo extends CrudRepository<RecurringUnavailability,Integer> {

    //////////////////////////////////////// READ OPERATIONS ////////////////////////////////////////
    public List<RecurringUnavailability> findByEmployeeEmployeeID(int employee_id);
    public List<RecurringUnavailability> findByWeekday(int weekday);
//    public List<RecurringUnavailability> getRecurringUnavailabilityByTime(long startTime, long endTime);
    //////////////////////////////////////////////////////////////////////////////////////////////////

}

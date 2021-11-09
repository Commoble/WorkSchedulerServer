package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.ScheduledShift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledShiftRepo extends CrudRepository<ScheduledShift, Integer>
{

    List<ScheduledShift> findByDate(long date);
    List<ScheduledShift> findByScheduledShiftID(int id);
}

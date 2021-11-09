package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.RecurringUnavailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringUnavailabilityRepo extends CrudRepository<RecurringUnavailability,Integer> {

}

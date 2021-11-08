package com.revature.workscheduler.repositories;

import com.revature.workscheduler.models.ShiftType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftTypeRepo extends CrudRepository<ShiftType, Integer>
{
}

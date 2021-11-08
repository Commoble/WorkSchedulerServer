package com.revature.workscheduler.services;

import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.repositories.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeServiceImpl implements ShiftTypeService
{
    @Autowired
    ShiftTypeRepo str;

    @Override
    public ShiftTypeRepo getRepo()
    {
        return str;
    }

    @Override
    public Integer getIDFor(ShiftType value)
    {
        return value.getShiftTypeID();
    }
}

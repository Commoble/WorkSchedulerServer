package com.revature.workscheduler.controllers;

import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.services.ShiftTypeService;
import com.revature.workscheduler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShiftTypeController
{
    @Autowired
    private ShiftTypeService sts;

    @GetMapping("/shifttypes")
    public List<ShiftType> getShiftTypes()
    {
        return sts.getAll();
    }

    @PostMapping(value = "/shifttypes", consumes = "application/json")
    public ShiftType addShiftType(@RequestBody ShiftType shift)
    {
        return sts.add(shift);
    }

    @PutMapping(value = "/shifttypes/{id}", consumes = "application/json")
    public ShiftType updateShiftType(@RequestBody ShiftType shift, @PathVariable("id") String id)
    {
        shift.setShiftTypeID(ParseUtils.safeParseInt(id, 0));
        return sts.update(shift);
    }

    @DeleteMapping(value = "/shifttypes/{id}")
    public boolean deleteShiftType(@PathVariable("id") String id)
    {
        return sts.delete(ParseUtils.safeParseInt(id, 0));
    }
}

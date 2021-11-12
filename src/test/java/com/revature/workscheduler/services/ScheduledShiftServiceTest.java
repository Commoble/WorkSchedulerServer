package com.revature.workscheduler.services;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class ScheduledShiftServiceTest {

    @Autowired
    private ScheduledShiftService ss;

    @MockBean
    private ScheduledShiftRepo sr;

    @Test
    void addScheduledShift() {
        ScheduledShift MS = new ScheduledShift(0, new ShiftType(), new Employee(), 1);

        Mockito.when(sr.save(MS))
                .thenReturn(new ScheduledShift(1, MS.getShiftType(), MS.getEmployee(), MS.getDate()));
        MS = ss.add(MS);

        Assertions.assertEquals(1, MS.getScheduledShiftID());
    }

    @Test
    void getAllScheduledShifts()
    {
        List<ScheduledShift> stList = new LinkedList<>();
        List<ScheduledShift> stList2 = ss.getAll();
        Assertions.assertEquals(stList.getClass(), stList2.getClass());
    }

    @Test
    void getScheduledShiftsForEmployeeGetsScheduledShifts()
    {
        Assertions.assertTrue(false); // TODO write tests
    }

    @Test
    void getScheduledShiftsForEmployeeDoesntGetScheduledShiftsForMissingEmployee()
    {
        Assertions.assertTrue(false); // TODO write tests
    }


}

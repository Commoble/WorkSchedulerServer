package com.revature.workscheduler.services;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;
import com.revature.workscheduler.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
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
    void getScheduledShiftID()
    {
        int id = 5;
        ScheduledShift shift = new ScheduledShift();
        shift.setScheduledShiftID(id);
        Assertions.assertEquals(id, this.ss.getIDFor(shift));

    }

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
        Assertions.assertNotNull(this.ss.getAll());
    }

    @Test
    void getScheduledShiftsForEmployeeGetsScheduledShifts()
    {
        int employeeID = 1;
        int scheduledShiftID = 1;
        Employee employee = ModelGenerators.makeRandomEmployee();
        employee.setEmployeeID(employeeID);
        ShiftType shiftType = new ShiftType(1, "Test Shift", 0, 3600000);
        ScheduledShift scheduledShift = new ScheduledShift(scheduledShiftID, shiftType, employee, 0);
        Mockito.when(this.sr.findByEmployeeEmployeeID(employeeID))
            .thenReturn(Collections.singletonList(scheduledShift));
        List<ScheduledShift> shifts = this.ss.getScheduledShiftsForEmployee(employeeID);
        Assertions.assertEquals(1, shifts.size());
        for (ScheduledShift actualShift : shifts)
        {
            Assertions.assertEquals(employeeID, actualShift.getEmployee().getEmployeeID());
        }
    }

    @Test
    void getScheduledShiftsForEmployeeDoesntGetScheduledShiftsForMissingEmployee()
    {
        int employeeID = 1;
        Mockito.when(this.sr.findByEmployeeEmployeeID(employeeID))
            .thenReturn(Collections.emptyList());
        List<ScheduledShift> shifts = this.ss.getScheduledShiftsForEmployee(employeeID);
        Assertions.assertTrue(shifts.isEmpty());
    }


}

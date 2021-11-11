package com.revature.workscheduler.services;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.repositories.ScheduledShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class ScheduledShiftServiceTest {

    @Autowired
    private ScheduledShiftService ss;

    @MockBean
    private ScheduledShiftRepo sr;



//    @Test
//    void addScheduledShift()
//    {
//        ScheduledShift shift = new ScheduledShift(0, new ShiftType(), new Employee(), 10102021);
//
//        Mockito.when(sr.save(shift)).thenReturn(new ScheduledShift(1, shift.getShiftType(), shift.getEmployee(), shift.getDate()));
//
//        shift = ss.add(shift);
//
//        Assertions.assertEquals(1, shift.getScheduledShiftID());
//        Assertions.assertEquals(10102021, shift.getDate());
//
//    }
//    @Test
//    void deleteScheduledShift() {
//        ScheduledShift shift = new ScheduledShift(0, ss.getShiftType(), ss.getEmployee(), 10102021);
//
//        Mockito.doNothing().when(ss).delete(shift.getScheduledShiftID());
//
//        boolean result = sr.delete(shift.getScheduledShiftID());
//        Assertions.assertTrue(result);
//    }






}

package com.revature.workscheduler.services;
import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.zaxxer.hikari.metrics.micrometer.MicrometerMetricsTracker;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.revature.workscheduler.models.RecurringUnavailability;
import com.revature.workscheduler.repositories.RecurringUnavailabilityRepo;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest(classes= WorkschedulerApplication.class)
public class RecurringUnavailabilityServiceTests {

    @Autowired
    private RecurringUnavailabilityService service;
    @MockBean
    private RecurringUnavailabilityRepo rur;

    @Test
    @Rollback
    void createRecurringUnavailability(){
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,1, /*Monday*/61200000, 18000000);
        int oldID = newRUR.getRecurringUnavailabilityID();
        Mockito.when(rur.save(newRUR)).thenReturn(new RecurringUnavailability(1, newRUR.getEmployee(), newRUR.getWeekday(), newRUR.getStartTime(), newRUR.getEndTime()));
        RecurringUnavailability actualRUR = service.add(newRUR);
        int newID = actualRUR.getRecurringUnavailabilityID();
        Assertions.assertNotEquals(oldID, newID);
        Assertions.assertEquals(newRUR.getEmployee(), actualRUR.getEmployee());
        Assertions.assertEquals(newRUR.getWeekday(), actualRUR.getWeekday());
        Assertions.assertEquals(newRUR.getStartTime(), actualRUR.getStartTime());
        Assertions.assertEquals(newRUR.getEndTime(), actualRUR.getEndTime());
    }

    @Test
    @Rollback
    void updateRecurringUnavailability(){
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,1, /*Monday*/61200000, 18000000);
        int oldID = newRUR.getRecurringUnavailabilityID();
        Mockito.when(rur.save(newRUR)).thenReturn(newRUR);
        Mockito.when(rur.findById(oldID)).thenReturn(Optional.of(newRUR));
        RecurringUnavailability actualRUR = service.update(newRUR);
        int newID = actualRUR.getRecurringUnavailabilityID();
        Assertions.assertEquals(oldID, newID);
        Assertions.assertEquals(newRUR.getEmployee(), actualRUR.getEmployee());
        Assertions.assertEquals(newRUR.getWeekday(), actualRUR.getWeekday());
        Assertions.assertEquals(newRUR.getStartTime(), actualRUR.getStartTime());
        Assertions.assertEquals(newRUR.getEndTime(), actualRUR.getEndTime());
    }

    @Test
    @Rollback
    void deleteRecurringUnavailability(){
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,1, /*Monday*/61200000, 18000000);
        int oldID = newRUR.getRecurringUnavailabilityID();
        Optional<RecurringUnavailability> optionalRecurringUnavailability = Optional.of(newRUR);
        Mockito.when(rur.findById(oldID)).thenReturn(optionalRecurringUnavailability);
        Mockito.doNothing().when(rur).deleteById(oldID);
        boolean deleted = service.delete(oldID);
        Assertions.assertTrue(deleted);
    }

    @Test
    @Rollback
    void getRecurringUnavailabilityByEmployee(){
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,1, /*Monday*/61200000, 18000000);
        int empID = employee.getEmployeeID();
        Employee newRURsEmp = newRUR.getEmployee();
        int newRURsEmpId = newRURsEmp.getEmployeeID();
        Assertions.assertEquals(empID, newRURsEmpId);
        RecurringUnavailability actualRUR = service.add(newRUR);
        List<RecurringUnavailability> listAll = service.getAll();
        service.getRecurringUnavailabilityByEmployee(empID);
        for(RecurringUnavailability x : listAll) {
            int derivedID = x.getEmployee().getEmployeeID();
            System.out.println(derivedID);
//            System.out.println("HELLO WORLD");
            if (derivedID == empID){ Assertions.assertEquals(newRUR, x);}
        }


    }

    @Test
    @Rollback
    void getRecurringUnavailabilityByWeekday(){
        int weekday = 1;
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,weekday, /*Monday*/61200000, 18000000);
        List<RecurringUnavailability> actualRUR = rur.findByWeekday(weekday);
        try {
            actualRUR.add(newRUR);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        for(RecurringUnavailability x : actualRUR) {
            if (weekday == x.getWeekday()){ Assertions.assertEquals(newRUR, x);}
        }
        List<RecurringUnavailability> retrievedRUR = service.getRecurringUnavailabilityByWeekday(weekday);
//        actualRUR.get(0).getWeekday()
//        List<RecurringUnavailability> listAll = service.getAll();
//        List<RecurringUnavailability> foundRURs = rur.findByWeekday(weekday);
//        Mockito.when(rur.findByWeekday(weekday)).thenReturn(true);
//        Assertions.assertEquals(foundRURs, newRUR);
//         RecurringUnavailability foundRUR = foundRURs.get(0);
//         Assertions.assertEquals(foundRUR, newRUR);

    }

    @Test
    void getRecurringUnavailabilityByTime(){
        service.getRecurringUnavailabilityByTime(61200000, 18000000);
    }
}

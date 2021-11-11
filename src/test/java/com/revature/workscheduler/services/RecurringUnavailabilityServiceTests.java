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

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class RecurringUnavailabilityServiceTests {

    @Autowired
    private RecurringUnavailabilityService service;
    @MockBean
    private RecurringUnavailabilityRepo rur;

    @Test
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
    void getRecurringUnavailabilityByEmployee(){
        Employee employee = new Employee(1, "Steve Testingperson", "stevet", "parseword", 0);
        RecurringUnavailability newRUR = new RecurringUnavailability(employee,1, /*Monday*/61200000, 18000000);
        int empID = employee.getEmployeeID();
        Employee newRURsEmp = newRUR.getEmployee();
        int newRURsEmpId = newRURsEmp.getEmployeeID();

        Assertions.assertEquals(empID, newRURsEmpId);

        RecurringUnavailability actualRUR = service.add(newRUR);
        List<RecurringUnavailability> listAll = service.getAll();
        for(RecurringUnavailability x : listAll) {
            int derivedID = x.getEmployee().getEmployeeID();
            System.out.println(derivedID);
            System.out.println("HELLO WORLD");
            if (derivedID == empID){ Assertions.assertEquals(newRUR, x);}
        }
    }

    @Test
    void getRecurringUnavailabilityByWeekday(){}

    @Test
    void getRecurringUnavailabilityByTime(){}
}

package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.repositories.TimeOffRequestRepo;
import com.revature.workscheduler.testutils.ModelGenerators;
import org.assertj.core.util.Lists;
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

@SpringBootTest(classes = WorkschedulerApplication.class)
public class TimeOffRequestServiceTests
{
    @Autowired
    private TimeOffRequestService service;

    @MockBean
    private TimeOffRequestRepo repo;

    @Test
    void addTimeOffRequest()
    {
        TimeOffRequest st = new TimeOffRequest(0, new Employee(), 0, 0, null);
        int oldId = st.getTimeOffRequestID();
        Mockito.when(repo.save(st))
                .thenReturn(new TimeOffRequest(1, new Employee(), 0, 0, null));
        TimeOffRequest newSt = service.add(st);
        Assertions.assertNotEquals(oldId, newSt.getTimeOffRequestID());
    }

    @Test
    void getTimeOffRequest()
    {
        TimeOffRequest st = new TimeOffRequest(new Employee(), 0, 0, null);
        int oldID = service.getIDFor(st);
        Mockito.when(repo.findById(oldID))
                .thenReturn(Optional.of(st));
        TimeOffRequest newSt = service.get(oldID);
        Assertions.assertEquals(oldID, newSt.getTimeOffRequestID());
    }

    @Test
    void getAllTimeOffRequests()
    {
        List<TimeOffRequest> stList = new ArrayList<>();
        List<TimeOffRequest> stList2 = service.getAll();
        Assertions.assertEquals(stList.getClass(), stList2.getClass());
    }

    @Test
    void updateTimeOffRequest()
    {
        TimeOffRequest st = new TimeOffRequest(new Employee(), 0, 0, null);
        Mockito.when(repo.save(st))
                .thenReturn(st);
        Mockito.when(repo.findById(st.getTimeOffRequestID()))
                .thenReturn(Optional.of(st));
        TimeOffRequest newSt = service.update(st);
        Assertions.assertEquals(newSt, st);
    }

    @Test
    void deleteTimeOffRequestById()
    {
        TimeOffRequest st = new TimeOffRequest(new Employee(), 0, 0, null);
        Mockito.when(repo.findById(st.getTimeOffRequestID()))
                .thenReturn(Optional.of(st));
        boolean deletedTrue = service.delete(st.getTimeOffRequestID());
        boolean deletedFalse = service.delete(null);
        Assertions.assertTrue(deletedTrue);
        Assertions.assertFalse(deletedFalse);
    }

    @Test
    void getAllTimeOffRequestsByEmployeeID()
    {
        List<TimeOffRequest> stList = new LinkedList<>();
        List<TimeOffRequest> stList2 = service.getByEmployeeID(0);
        Assertions.assertEquals(stList.getClass(), stList2.getClass());
    }

    @Test
    void getAllPendingTimeOffRequests()
    {
        List<TimeOffRequest> stList = new LinkedList<>();
        List<TimeOffRequest> stList2 = service.getPendingRequests();
        Assertions.assertEquals(stList.getClass(), stList2.getClass());
    }

    @Test
    void getNotDeniedRequestsForEmployee()
    {
        int employeeID = 1;
        Employee employee = ModelGenerators.makeRandomEmployee();
        employee.setEmployeeID(employeeID);
        TimeOffRequest request = new TimeOffRequest(1, employee, 0, 1, true);
        List<TimeOffRequest> list = Lists.list(request);
        Mockito.when(this.repo.findByEmployeeEmployeeIDAndApprovedNotFalse(employeeID))
            .thenReturn(list);
        Assertions.assertEquals(list, this.service.getNotDeniedRequestsForEmployee(employeeID));
    }
}

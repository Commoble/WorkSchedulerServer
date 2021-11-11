package com.revature.workscheduler.controllers;

import com.google.gson.Gson;
import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.services.TimeOffRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes = WorkschedulerApplication.class)
public class TimeOffRequestControllerTests
{
    private static final Gson gson = new Gson();

    @MockBean
    private TimeOffRequestService service;

    @Autowired
    private MockMvc mvc;

    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void getPendingRequests() throws Exception
    {
        List<TimeOffRequest> requests = new ArrayList<>();
        Mockito.when(service.getPendingRequests())
                .thenReturn(requests);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/timeoff"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void getRequestsByEmpID() throws Exception
    {
        List<TimeOffRequest> requests = new ArrayList<>();
        Mockito.when(service.getByEmployeeID(0))
                .thenReturn(requests);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/timeoff/0"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void addRequest() throws Exception
    {
        TimeOffRequest req = new TimeOffRequest(0, new Employee(), 0, 0, null);


        System.out.println(gson.toJson(req));
        Mockito.when(service.add(req))
                .thenReturn(req);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/timeoff")
                .contentType("application/json")
                .content("{\"timeOffRequestID\":0,\"employee\":{\"employeeID\":0,\"name\":\"\",\"username\":\"\",\"password\":\"\",\"startDate\":0},\"startTime\":0,\"endTime\":0}"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void patchApproval() throws Exception
    {
        TimeOffRequest req = new TimeOffRequest(0, new Employee(), 0, 0, null);

        Mockito.when(service.update(req))
                .thenReturn(req);
        Mockito.when(service.get(0))
                .thenReturn(req);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.patch("/timeoff/0?approved=true"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

}

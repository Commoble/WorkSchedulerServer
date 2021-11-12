package com.revature.workscheduler.controllers;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.models.TimeOffRequest;
import com.revature.workscheduler.services.ScheduledShiftService;
import com.revature.workscheduler.services.ScheduledShiftServiceTest;
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
@SpringBootTest(classes= WorkschedulerApplication.class)



public class ScheduledShiftControllerTests
{

    @MockBean
    private ScheduledShiftService ss;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void getScheduledShift() throws Exception {
        Mockito.when(ss.get(1)).thenReturn(new ScheduledShift(1, new ShiftType(), new Employee(), 0));
        ResultActions c = mvc.perform(MockMvcRequestBuilders.get("/schedule/1"));
        c.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
    @Test
    void getAllScheduledShift() throws Exception
    {
        List<ScheduledShift> schedule = new ArrayList<>();
        Mockito.when(ss.getAll())
                .thenReturn(schedule);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/schedules"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
//
//    @WithMockUser(username = "user", password = "pass", roles = {"USER, MANAGER"})
//    @Test
//    void updateScheduledShift() throws Exception
//    {
//        ScheduledShift updated = new ScheduledShift(1,new ShiftType(), new Employee(),0);
//
//        Mockito.when(ss.update(updated))
//                .thenReturn(updated);
//        Mockito.when(ss.get(1))
//                .thenReturn(updated);
//        ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/schedule/1")
//                .contentType("application/json")
//                .content("{}"));
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }




}

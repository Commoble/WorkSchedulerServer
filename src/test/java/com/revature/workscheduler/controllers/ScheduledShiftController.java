package com.revature.workscheduler.controllers;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.models.ShiftType;
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

@AutoConfigureMockMvc
@SpringBootTest(classes= WorkschedulerApplication.class)



public class ScheduledShiftController {

    @MockBean
    private ScheduledShiftService ss;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "stevet", password = "parseword", roles = "User")
    void getScheduledShift() throws Exception {
        Mockito.when(ss.get(1)).thenReturn(new ScheduledShift(1, new ShiftType(), new Employee(), 10112021));
        ResultActions c = mvc.perform(MockMvcRequestBuilders.get("/schedule/1"));
        c.andExpect(MockMvcResultMatchers.status().isOk());

    }





}

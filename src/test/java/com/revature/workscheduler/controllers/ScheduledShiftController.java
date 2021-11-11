package com.revature.workscheduler.controllers;


import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.ScheduledShift;
import com.revature.workscheduler.services.ScheduledShiftService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

//    @Test
//    void getScheduledShift() throws Exception {
//        Mockito.when(ss.get(1)).thenReturn(new ScheduledShift(1, ss.getShiftType, "Jessica", 10112021));
//
//        ResultActions a = mvc.perform(MockMvcRequestBuilders.get("/scheduleshift/1"));
//        a.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }



}

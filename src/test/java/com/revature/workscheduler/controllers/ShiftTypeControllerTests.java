package com.revature.workscheduler.controllers;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.services.ShiftTypeService;
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

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes = WorkschedulerApplication.class)
public class ShiftTypeControllerTests
{
    @MockBean
    private ShiftTypeService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void getShiftTypes() throws Exception
    {
        List<ShiftType> shiftTypes = new ArrayList<>();
        Mockito.when(service.getAll())
                .thenReturn(shiftTypes);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/shifttypes"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addShiftType() throws Exception
    {
        ShiftType st = new ShiftType(0,"name", 0,0);
        Mockito.when(service.add(st))
                .thenReturn(st);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/shifttypes"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

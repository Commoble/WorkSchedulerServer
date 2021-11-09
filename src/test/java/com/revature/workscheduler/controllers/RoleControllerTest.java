package com.revature.workscheduler.controllers;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.services.RoleService;
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
public class RoleControllerTest
{
    @MockBean
    private RoleService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void getEmployeeByID() throws Exception
    {
        Role expectedRole = new Role(1, "Test Role", true);
        Mockito.when(service.get(1))
                .thenReturn(expectedRole);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/roles/1"));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
package com.revature.workscheduler.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.services.RoleService;
import org.junit.jupiter.api.Assertions;
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

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes= WorkschedulerApplication.class)
public class RoleControllerTest
{
    private static final Gson GSON = new Gson();
    private static final Type ROLE_LIST_TYPE = new TypeToken<List<Role>>(){}.getType(); // for gson

    @MockBean
    private RoleService service;

    @Autowired
    private MockMvc mvc;

//    @Test
//    @WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
//    void getRoles() throws Exception
//    {
//        Role expectedRole = new Role(1, "Test Role", true);
//        List<Role> expectedRoles = Collections.singletonList(expectedRole);
//        Mockito.when(service.getAll())
//            .thenReturn(expectedRoles);
//        String response = mvc.perform(MockMvcRequestBuilders.get("/roles"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andReturn()
//            .getResponse()
//            .getContentAsString();
//        List<Role> actualRoles = GSON.fromJson(response, ROLE_LIST_TYPE);
//        Assertions.assertEquals(1, actualRoles.size());
//    }
//
//    @Test
//    @WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
//    void getRoleByID() throws Exception
//    {
//        Role expectedRole = new Role(1, "Test Role", true);
//        Mockito.when(service.get(1))
//                .thenReturn(expectedRole);
//        String response = mvc.perform(MockMvcRequestBuilders.get("/roles/1"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andReturn()
//            .getResponse()
//            .getContentAsString();
//        Role actualRole = GSON.fromJson(response, Role.class);
//        Assertions.assertEquals(expectedRole.getRoleID(), actualRole.getRoleID());
//        Assertions.assertEquals(expectedRole.getName(), actualRole.getName());
//        Assertions.assertEquals(expectedRole.isManager(), actualRole.isManager());
//    }
}
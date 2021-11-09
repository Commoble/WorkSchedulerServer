package com.revature.workscheduler.controllers;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
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
public class EmployeeControllerTests
{
	@MockBean
	private EmployeeService service;

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER","MANAGER"})
	void getEmployeeByID() throws Exception
	{
		int id = 1;
		Employee expectedEmployee = new Employee(id, "Steve Testingperson","stevet", "parseword", 0);
		Mockito.when(service.get(id))
			.thenReturn(expectedEmployee);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/employees/1"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
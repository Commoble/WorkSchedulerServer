package com.revature.workscheduler.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import com.revature.workscheduler.testutils.ModelGenerators;
import com.revature.workscheduler.webmodels.EmployeeResponse;
import com.revature.workscheduler.webmodels.LoginResponse;
import org.assertj.core.util.Lists;
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
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes= WorkschedulerApplication.class)
public class EmployeeControllerTests
{
	private static final Gson GSON = new Gson();
	private static final Type EMPLOYEE_RESPONSE_LIST_TYPE = new TypeToken<List<EmployeeResponse>>(){}.getType();

	@MockBean
	private EmployeeService service;

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username="stevet", password="parseword", roles="USER")
	void getLoggedInEmployeeGetsLoggedInEmployee() throws Exception
	{
		int id = 1;
		Employee expectedEmployee = new Employee(id, "Steve Testingperson","stevet", "parseword", 12);
		Mockito.when(service.getLoggedInEmployee())
			.thenReturn(expectedEmployee);
		Mockito.when(service.isEmployeeManager(id))
			.thenReturn(false);
		String response = mvc.perform(MockMvcRequestBuilders.get("/login"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		LoginResponse actualResponse = GSON.fromJson(response, LoginResponse.class);
		Assertions.assertFalse(actualResponse.isManager());
		EmployeeResponse actualEmployee = actualResponse.getEmployee();
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
		Assertions.assertEquals(expectedEmployee.getStartDate(), actualEmployee.getStartDate());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
	void getLoggedInManagerGetsLoggedInManager() throws Exception
	{
		int id = 1;
		Employee expectedEmployee = new Employee(id, "Steve Testingperson","stevet", "parseword", 12);
		Mockito.when(service.getLoggedInEmployee())
			.thenReturn(expectedEmployee);
		Mockito.when(service.isEmployeeManager(id))
			.thenReturn(true);
		String response = mvc.perform(MockMvcRequestBuilders.get("/login"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		LoginResponse actualResponse = GSON.fromJson(response, LoginResponse.class);
		Assertions.assertTrue(actualResponse.isManager());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={})
	void getLoggedInEmployeeDoesntGetUnauthorizedUser() throws Exception
	{
		int id = 1;
		mvc.perform(MockMvcRequestBuilders.get("/login"))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

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

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={})
	void getEmployeeByIDDoesntGetEmployeeIfUnauthorized() throws Exception
	{
		int id = 1;
		Employee expectedEmployee = new Employee(id, "Steve Testingperson","stevet", "parseword", 0);
		mvc.perform(MockMvcRequestBuilders.get("/employees/1"))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
	void getEmployeeByIDDoesntGetEmployeeIfBadID() throws Exception
	{
		Mockito.when(this.service.get(0))
			.thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.get("/employees/five"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
	void getEmployees() throws Exception
	{
		Employee expectedEmployee = ModelGenerators.makeRandomEmployee();
		expectedEmployee.setEmployeeID(1);
		List<Employee> expectedEmployees = Lists.list(expectedEmployee);
		Mockito.when(this.service.getAll())
			.thenReturn(expectedEmployees);
		String response = mvc.perform(MockMvcRequestBuilders.get("/employees"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		List<EmployeeResponse> actualEmployees = GSON.fromJson(response, EMPLOYEE_RESPONSE_LIST_TYPE);
		Assertions.assertEquals(1, actualEmployees.size());
		EmployeeResponse actualEmployee = actualEmployees.get(0);
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
	void getEmployeesByShiftType() throws Exception
	{
		Employee expectedEmployee = ModelGenerators.makeRandomEmployee();
		expectedEmployee.setEmployeeID(1);
		List<Employee> expectedEmployees = Lists.list(expectedEmployee);
		Mockito.when(this.service.getAssignableEmployees(1))
			.thenReturn(expectedEmployees);
		String response = mvc.perform(MockMvcRequestBuilders.get("/employees?shiftType=1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		List<EmployeeResponse> actualEmployees = GSON.fromJson(response, EMPLOYEE_RESPONSE_LIST_TYPE);
		Assertions.assertEquals(1, actualEmployees.size());
		EmployeeResponse actualEmployee = actualEmployees.get(0);
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER", "MANAGER"})
	void getEmployeesByShiftTypeAndTime() throws Exception
	{
		Employee expectedEmployee = ModelGenerators.makeRandomEmployee();
		expectedEmployee.setEmployeeID(1);
		List<Employee> expectedEmployees = Lists.list(expectedEmployee);
		Mockito.when(this.service.getAssignableEmployees(1, 0, 1))
			.thenReturn(expectedEmployees);
		String response = mvc.perform(MockMvcRequestBuilders.get("/employees?shiftType=1&startTime=0&endTime=1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		List<EmployeeResponse> actualEmployees = GSON.fromJson(response, EMPLOYEE_RESPONSE_LIST_TYPE);
		Assertions.assertEquals(1, actualEmployees.size());
		EmployeeResponse actualEmployee = actualEmployees.get(0);
		Assertions.assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
		Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
	}

	@Test
	@WithMockUser(username="stevet", password="parseword", roles={"USER"})
	void getEmployeesWhileUnauthorized() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/employees/1"))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
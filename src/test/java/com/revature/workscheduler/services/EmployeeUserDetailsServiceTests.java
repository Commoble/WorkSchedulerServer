package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class EmployeeUserDetailsServiceTests
{
	@Autowired
	UserDetailsService service;

	@MockBean
	EmployeeService employeeService;

	@Test
	void loadUserByUsernameLoadsUser()
	{
		String username = "stevet";
		Employee mockEmployee = new Employee(1, "Steve Testperson", username, "password", 0);
		String[] expectedRoles = {"ROLE_USER"};
		Mockito.when(employeeService.getEmployeeByUsername(username))
			.thenReturn(mockEmployee);
		UserDetails userDetails = this.service.loadUserByUsername(username);
		Assertions.assertEquals(username, userDetails.getUsername());
		for (String role : expectedRoles)
		{
			Assertions.assertTrue(userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(role::equals));
		}
	}

	@Test
	void loadManagerByUsernameLoadsManager()
	{
		int id = 1;
		String username = "stevet";
		Employee mockEmployee = new Employee(id, "Steve Testperson", username, "password", 0);
		String[] expectedRoles = {"ROLE_USER", "ROLE_MANAGER"};
		Mockito.when(employeeService.getEmployeeByUsername(username))
			.thenReturn(mockEmployee);
		Mockito.when(employeeService.isEmployeeManager(id))
			.thenReturn(true);
		UserDetails userDetails = this.service.loadUserByUsername(username);
		Assertions.assertEquals(username, userDetails.getUsername());
		for (String role : expectedRoles)
		{
			Assertions.assertTrue(userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(role::equals));
		}
	}

	@Test
	void loadMissingUserThrowsException()
	{
		String username = "stevet";
		Mockito.when(employeeService.getEmployeeByUsername(username))
			.thenReturn(null);
		Assertions.assertThrows(UsernameNotFoundException.class, ()->this.service.loadUserByUsername(username));
	}
}

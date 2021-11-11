package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeUserDetailsService implements UserDetailsService
{
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Locates the user based on the username. In the actual implementation, the search
	 * may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured. In this case, the <code>UserDetails</code>
	 * object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 *                                   GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Employee employee = this.employeeService.getEmployeeByUsername(username);
		if (employee == null)
			throw new UsernameNotFoundException("No employee found for username " + username);
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		if (this.employeeService.isEmployeeManager(employee.getEmployeeID()))
		{
			roles.add("MANAGER");
		}
		return User.builder()
			.username(employee.getUsername())
			.password(employee.getPassword())
			.passwordEncoder(passwordEncoder::encode)
			.roles(roles.toArray(new String[roles.size()]))
			.build();
	}
}

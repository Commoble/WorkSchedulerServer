package com.revature.workscheduler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="employee_id", updatable=false, nullable = false, unique=true)
	private int employeeID;

	@Column(name="name", nullable=false, length=50)
	private String name;

	@Column(name="username", nullable=false, length=50)
	private String username;

	@Column(name="password", nullable=false, length=50)
	private String password;

	@Column(name="start_date", nullable=false)
	private long startDate;

	public Employee()
	{
		this("", "", "", 0);
	}

	public Employee(String name, String username, String password, long startDate)
	{
		this(0, name, username, password, startDate);
	}

	public Employee(int employeeID, String name, String username, String password, long startDate)
	{
		this.employeeID = employeeID;
		this.name = name;
		this.username = username;
		this.password = password;
		this.startDate = startDate;
	}

	public int getEmployeeID()
	{
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public long getStartDate()
	{
		return this.startDate;
	}

	public void setStartDate(long startDate)
	{
		this.startDate = startDate;
	}
}

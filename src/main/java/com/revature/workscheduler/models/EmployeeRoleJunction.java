package com.revature.workscheduler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_role_junctions")
public class EmployeeRoleJunction
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="employee_role_junction_id", updatable=false, nullable = false, unique=true)
	private int employeeRoleJunctionID;

	@ManyToOne
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	private Role roleID;

	public EmployeeRoleJunction()
	{
		this(0, null, null);
	}

	public EmployeeRoleJunction(Employee employee, Role roleID)
	{
		this(0, employee, roleID);
	}

	public EmployeeRoleJunction(int employeeRoleJunctionID, Employee employee, Role roleID)
	{
		this.employeeRoleJunctionID = employeeRoleJunctionID;
		this.employee = employee;
		this.roleID = roleID;
	}

	public int getEmployeeRoleJunctionID()
	{
		return this.employeeRoleJunctionID;
	}

	public void setEmployeeRoleJunctionID(int employeeRoleJunctionID)
	{
		this.employeeRoleJunctionID = employeeRoleJunctionID;
	}

	public Employee getEmployee()
	{
		return this.employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public Role getRoleID()
	{
		return this.roleID;
	}

	public void setRoleID(Role roleID)
	{
		this.roleID = roleID;
	}
}

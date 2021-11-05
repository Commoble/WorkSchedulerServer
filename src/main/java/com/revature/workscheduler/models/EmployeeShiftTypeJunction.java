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
@Table(name="employee_shift_type_junctions")
public class EmployeeShiftTypeJunction
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="employee_shift_type_junction_id", updatable=false, nullable = false, unique=true)
	private int employeeShiftTypeJunctionID;

	@ManyToOne
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

	@ManyToOne
	@JoinColumn(name="shift_type_id", nullable=false)
	private ShiftType shiftType;

	public EmployeeShiftTypeJunction(Employee employee, ShiftType shiftType)
	{
		this(0, employee, shiftType);
	}

	public EmployeeShiftTypeJunction(int employeeShiftTypeJunctionID, Employee employee, ShiftType shiftType)
	{
		this.employeeShiftTypeJunctionID = employeeShiftTypeJunctionID;
		this.employee = employee;
		this.shiftType = shiftType;
	}

	public int getEmployeeShiftTypeJunctionID()
	{
		return this.employeeShiftTypeJunctionID;
	}

	public void setEmployeeShiftTypeJunctionID(int employeeShiftTypeJunctionID)
	{
		this.employeeShiftTypeJunctionID = employeeShiftTypeJunctionID;
	}

	public Employee getEmployee()
	{
		return this.employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public ShiftType getShiftType()
	{
		return this.shiftType;
	}

	public void setShiftType(ShiftType shiftType)
	{
		this.shiftType = shiftType;
	}
}

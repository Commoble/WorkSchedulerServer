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
@Table(name="scheduled_shifts")
public class ScheduledShift
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="scheduled_shift_id", updatable = false, nullable = false, unique=true)
	private int scheduledShiftID;

	@ManyToOne
	@JoinColumn(name="shift_type_id", nullable = false)
	private ShiftType shiftType;

	@ManyToOne
	@JoinColumn(name="employee_id", nullable = false)
	private Employee employee;

	@Column(name="date", nullable=false)
	private long date;

	public ScheduledShift() {
	}

	public ScheduledShift(ShiftType shiftType, Employee employee, long date)
	{
		this(0, shiftType, employee, date);
	}

	public ScheduledShift(int scheduledShiftID, ShiftType shiftType, Employee employee, long date)
	{
		this.scheduledShiftID = scheduledShiftID;
		this.shiftType = shiftType;
		this.employee = employee;
		this.date = date;
	}

	public int getScheduledShiftID()
	{
		return this.scheduledShiftID;
	}

	public void setScheduledShiftID(int scheduledShiftID)
	{
		this.scheduledShiftID = scheduledShiftID;
	}

	public ShiftType getShiftType()
	{
		return this.shiftType;
	}

	public void setShiftType(ShiftType shiftType)
	{
		this.shiftType = shiftType;
	}

	public Employee getEmployee()
	{
		return this.employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public long getDate()
	{
		return this.date;
	}

	public void setDate(long date)
	{
		this.date = date;
	}
}

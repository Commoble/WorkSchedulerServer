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
@Table(name="time_off_requests")
public class TimeOffRequest
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="time_off_request_id", updatable = false, nullable = false, unique=true)
	private int timeOffRequestID;

	@ManyToOne
	@JoinColumn(name="employee_id", nullable = false)
	private Employee employee;

	@Column(name="start_time", nullable = false)
	private long startTime;

	@Column(name="end_time", nullable = false)
	private long endTime;

	@Column(name="approved", nullable=true)
	private Boolean approved;
	
	public TimeOffRequest()
	{
		this(new Employee(), 0,0,null);
	}

	public TimeOffRequest(Employee employee, long startTime, long endTime, Boolean approved)
	{
		this(0, employee, startTime, endTime, approved);
	}

	public TimeOffRequest(int timeOffRequestID, Employee employee, long startTime, long endTime, Boolean approved)
	{
		this.timeOffRequestID = timeOffRequestID;
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.approved = approved;
	}

	public int getTimeOffRequestID()
	{
		return this.timeOffRequestID;
	}

	public void setTimeOffRequestID(int timeOffRequestID)
	{
		this.timeOffRequestID = timeOffRequestID;
	}

	public Employee getEmployee()
	{
		return this.employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public long getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}

	public long getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
	}

	public Boolean getApproved()
	{
		return this.approved;
	}

	public void setApproved(Boolean approved)
	{
		this.approved = approved;
	}
}

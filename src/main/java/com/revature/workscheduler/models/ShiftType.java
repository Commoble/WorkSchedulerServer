package com.revature.workscheduler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shift_types")
public class ShiftType
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="shift_type_id", updatable = false, nullable = false, unique=true)
	private int shiftTypeID;

	@Column(name="name", length=50, nullable=false)
	private String name;

	@Column(name="start_time", nullable=false)
	private long startTime;

	@Column(name="end_time", nullable=false)
	private long endTime;

	public ShiftType()
	{
		this("", 0, 0);
	}

	public ShiftType(String name, long startTime, long endTime)
	{
		this(0, name, startTime, endTime);
	}

	public ShiftType(int shiftTypeID, String name, long startTime, long endTime)
	{
		this.shiftTypeID = shiftTypeID;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getShiftTypeID()
	{
		return this.shiftTypeID;
	}

	public void setShiftTypeID(int shiftTypeID)
	{
		this.shiftTypeID = shiftTypeID;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
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
}

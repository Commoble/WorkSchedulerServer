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
@Table(name="recurring_unavailabilities")
public class RecurringUnavailability
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="recurring_unavailability_id", updatable = false, nullable = false, unique=true)
	private int recurringUnavailabilityID;

	@ManyToOne
	@JoinColumn(name="employee_id", nullable = false)
	private Employee employee;

	@Column(name="weekday", nullable = false)
	private int weekday;

	@Column(name="start_time", nullable = false)
	private long startTime;

	@Column(name="end_time", nullable = false)
	private long endTime;

	public RecurringUnavailability(Employee employee, int weekday, long startTime, long endTime)
	{
		this(0, employee, weekday, startTime, endTime);
	}

	public RecurringUnavailability(int recurringUnavailabilityID, Employee employee, int weekday, long startTime, long endTime)
	{
		this.recurringUnavailabilityID = recurringUnavailabilityID;
		this.employee = employee;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}

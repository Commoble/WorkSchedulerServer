package com.revature.workscheduler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="role_id", updatable = false, nullable = false, unique=true)
	private int roleID;

	@Column(name="can_create_shifts", nullable = false)
	private boolean canCreateShifts;

	@Column(name="can_assign_shifts", nullable = false)
	private boolean canAssignShifts;

	@Column(name="can_approve_time_off", nullable = false)
	private boolean canApproveTimeOff;

	public Role()
	{
		this(false, false, false);
	}

	public Role(boolean canCreateShifts, boolean canAssignShifts, boolean canApproveTimeOff)
	{
		this(0, canCreateShifts, canAssignShifts, canApproveTimeOff);
	}

	public Role(int roleID, boolean canCreateShifts, boolean canAssignShifts, boolean canApproveTimeOff)
	{
		this.roleID = roleID;
		this.canCreateShifts = canCreateShifts;
		this.canAssignShifts = canAssignShifts;
		this.canApproveTimeOff = canApproveTimeOff;
	}

	public int getRoleID()
	{
		return this.roleID;
	}

	public void setRoleID(int roleID)
	{
		this.roleID = roleID;
	}

	public boolean isCanCreateShifts()
	{
		return this.canCreateShifts;
	}

	public void setCanCreateShifts(boolean canCreateShifts)
	{
		this.canCreateShifts = canCreateShifts;
	}

	public boolean isCanAssignShifts()
	{
		return this.canAssignShifts;
	}

	public void setCanAssignShifts(boolean canAssignShifts)
	{
		this.canAssignShifts = canAssignShifts;
	}

	public boolean isCanApproveTimeOff()
	{
		return this.canApproveTimeOff;
	}

	public void setCanApproveTimeOff(boolean canApproveTimeOff)
	{
		this.canApproveTimeOff = canApproveTimeOff;
	}
}

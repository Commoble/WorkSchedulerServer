package com.revature.workscheduler.utils;

public class MathUtils
{
	/**
	 * @return whether two intervals overlap with each other (including if they share endpoints).
	 * Always returns false if either end is less than its start
	 */
	public static boolean doesTimeOverlap(long firstIntervalStart, long firstIntervalEnd, long secondIntervalStart, long secondIntervalEnd)
	{
		if (firstIntervalEnd < firstIntervalStart || secondIntervalEnd < secondIntervalStart)
			return false;
		return (firstIntervalStart >= secondIntervalStart && firstIntervalStart <= secondIntervalEnd)
			||(firstIntervalEnd >= secondIntervalStart && firstIntervalEnd <= secondIntervalEnd)
			||(secondIntervalStart >= firstIntervalStart && secondIntervalStart <= firstIntervalEnd)
			||(secondIntervalEnd >= firstIntervalStart && secondIntervalEnd <= firstIntervalEnd);
	}
}

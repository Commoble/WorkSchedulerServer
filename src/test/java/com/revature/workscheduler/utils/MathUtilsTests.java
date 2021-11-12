package com.revature.workscheduler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathUtilsTests
{
	@Test
	void doesTimeOverlap()
	{
		Assertions.assertTrue(MathUtils.doesTimeOverlap(0,2,1,1));
		Assertions.assertFalse(MathUtils.doesTimeOverlap(0,2,3,4));
		Assertions.assertFalse(MathUtils.doesTimeOverlap(3,4,0,2));
		Assertions.assertFalse(MathUtils.doesTimeOverlap(3,4,-2,-1));
	}
}

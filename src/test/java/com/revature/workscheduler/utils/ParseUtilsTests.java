package com.revature.workscheduler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseUtilsTests
{
	@Test
	void safeParseIntParsesInt()
	{
		int expected = 5;
		String five = "5";
		int actual = ParseUtils.safeParseInt(five, 0);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void safeParseIntDefaultsWhenInputNull()
	{
		int expected = 5;
		int actual = ParseUtils.safeParseInt(null, expected);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void safeParseIntDefaultsWhenParseFails()
	{
		int expected = 5;
		String input = "six";
		int actual = ParseUtils.safeParseInt(input, expected);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void safeParseLongParsesLong()
	{
		long expected = 5;
		String five = "5";
		long actual = ParseUtils.safeParseLong(five, 0L);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void safeParseLongDefaultsWhenInputNull()
	{
		long expected = 5;
		long actual = ParseUtils.safeParseLong(null, expected);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void safeParseLongDefaultsWhenParseFails()
	{
		long expected = 5;
		String input = "six";
		long actual = ParseUtils.safeParseLong(input, expected);
		Assertions.assertEquals(expected, actual);
	}
}

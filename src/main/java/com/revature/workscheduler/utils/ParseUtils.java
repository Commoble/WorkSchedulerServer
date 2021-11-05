package com.revature.workscheduler.utils;

public class ParseUtils
{
	/**
	 * Parses an input string as a base-10 integer
	 * @param input Input string representing an integer. Can be null.
	 * @param defaultInt Fallback integer to use if input cannot be parsed.
	 * @return Returns the input parsed as an integer if successful.
	 * Returns the default int if input is null or cannot be parsed.
	 */
	public static int safeParseInt(String input, int defaultInt)
	{
		if (input == null)
		{
			return defaultInt;
		}
		try
		{
			return Integer.parseInt(input, 10);
		}
		catch(NumberFormatException e)
		{
			return defaultInt;
		}
	}
}

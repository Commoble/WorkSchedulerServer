package com.revature.workscheduler.testutils;

import com.revature.workscheduler.models.Employee;

import java.util.Random;

public class ModelGenerators
{

	public static Employee makeRandomEmployee()
	{
		long time = System.currentTimeMillis();
		Random rand = new Random(time);
		int nameChars = 10 + rand.nextInt(10);
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<nameChars; i++)
		{
			char nextChar = (char) ('a' + rand.nextInt(26));
			builder.append(nextChar);
		}
		String name = builder.toString();
		return new Employee(name,name,name,time);
	}
}

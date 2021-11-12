package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = WorkschedulerApplication.class)
@Transactional
public class ScheduledShiftRepoTests
{
	@Autowired
	ScheduledShiftRepo repo;

	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsByEmployee()
	{
		Assertions.assertTrue(false); // TODO write test;
	}
	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsNothingForMissingEmployee()
	{
		Assertions.assertTrue(false); // TODO write test;
	}
}

package com.revature.workscheduler.repositories;

import com.revature.workscheduler.app.WorkschedulerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = WorkschedulerApplication.class)
@Transactional
public class TimeOffRequestRepoTests
{
	@Test
	@Rollback
	void findByEmployeeEmployeeIDFindsTimeOffRequests()
	{
		Assertions.assertTrue(false); // TODO write tests
	}

	@Test
	@Rollback
	void findByEmployeeEmployeeIDDoesntFindRequestsForMissingEmployee()
	{
		Assertions.assertTrue(false); // TODO write tests
	}

	@Test
	@Rollback
	void findByApprovedNullFindsRequests()
	{
		Assertions.assertTrue(false); // TODO write tests
	}

	@Test
	@Rollback
	void findByEmployeeEmployeeIDAndApprovedNotFalseFindsRequests()
	{
		Assertions.assertTrue(false); // TODO write tests
	}


}

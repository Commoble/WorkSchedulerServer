package com.revature.workscheduler.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.revature.workscheduler")
@EntityScan("com.revature.workscheduler.models")
@EnableJpaRepositories("com.revature.workscheduler.repositories")
public class WorkschedulerApplication
{

	public static void main(String[] args) {
		SpringApplication.run(WorkschedulerApplication.class, args);
	}

}

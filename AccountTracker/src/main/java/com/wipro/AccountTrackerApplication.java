package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = { "com.wipro" })
@EnableJpaAuditing
public class AccountTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountTrackerApplication.class, args);
	}
}

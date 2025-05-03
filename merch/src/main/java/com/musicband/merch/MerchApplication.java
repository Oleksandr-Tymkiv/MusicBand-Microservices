package com.musicband.merch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class MerchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchApplication.class, args);
	}

}

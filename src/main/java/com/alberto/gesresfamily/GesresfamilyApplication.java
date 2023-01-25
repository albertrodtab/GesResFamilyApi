package com.alberto.gesresfamily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GesresfamilyApplication {

//	@Autowired
//	private LocalDateTimeProvider localDateTimeProvider;
//
//	@Bean
//	public AuditingDateTimeProvider auditingDateTimeProvider() {
//		return localDateTimeProvider;
//	}

	public static void main(String[] args) {
		SpringApplication.run(GesresfamilyApplication.class, args);
	}

}

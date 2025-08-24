package com.Hm.hospital_patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipelinr;

@SpringBootApplication
public class HospitalPatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalPatientApplication.class, args);
	}

	@Bean
	public Pipelinr pipelinr(ApplicationContext context) {
		return new Pipelinr()
				.with(() -> context.getBeansOfType(Command.Handler.class).values().stream());
	}
}

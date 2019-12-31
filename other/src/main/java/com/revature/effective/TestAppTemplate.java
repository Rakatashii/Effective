package com.revature.effective;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class TestAppTemplate implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(TestApp.class);
	
	@Value("${value}")
	private String value;
	
	public static void main(String... args) {
		SpringApplication.run(TestApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

	}

}

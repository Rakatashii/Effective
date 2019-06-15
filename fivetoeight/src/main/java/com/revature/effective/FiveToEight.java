package com.revature.effective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.effective.enums.CalculatingService;

@SpringBootApplication
public class FiveToEight implements CommandLineRunner {
	
	@Autowired CalculatingService calculatingService;
	
    public static void main(String[] args) {
        SpringApplication.run(FiveToEight.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		calculatingService.calculate();
	}
}

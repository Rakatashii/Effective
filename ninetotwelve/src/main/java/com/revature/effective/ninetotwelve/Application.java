package com.revature.effective.ninetotwelve;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.effective.nintotwelve.multithreading.RunnableRefresher;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... args) throws Exception {
		RunnableRefresher r1 = new RunnableRefresher();
		r1.run();
	
	}
    
}

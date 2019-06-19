package com.revature.effective.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamsMain implements CommandLineRunner{
	
	@Autowired Functions functions;
	
	public static void main(String[] args) {
		SpringApplication.run(StreamsMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("5 * " + Math.PI + " = " + functions.applyFunc(5L));
		System.out.println("5 * " + Math.PI + " / 4 = " + functions.applyBiFunc(5L, 4L));
	}
}

package com.revature.effective;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.effective.enums.Calculator;

@SpringBootApplication
public class FiveToEight implements CommandLineRunner {
	
	@Autowired Calculator<Double> calculator;
	
    public static void main(String[] args) {
        SpringApplication.run(FiveToEight.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		List<ImmutablePair<Number, Number>> numbers = new ArrayList<>();
		numbers.add(ImmutablePair.of((short) 3, (short) 6));
		numbers.add(ImmutablePair.of(3, 6));
		numbers.add(ImmutablePair.of(3L, 6L));
		numbers.add(ImmutablePair.of(Short.valueOf((short) 3), Short.valueOf((short) 6)));
		numbers.add(ImmutablePair.of(Double.valueOf(3), Double.valueOf(6)));
		numbers.add(ImmutablePair.of(3.00, 6.00));
		
		numbers.forEach(pair -> {
			try {
				// same exact result for each type - Number::doubleValue makes them consistent
				System.out.println(pair.left + " + " + pair.right + " = " + Calculator.Operation.PLUS.apply(pair.left, pair.right));
				System.out.println(pair.left + " - " + pair.right + " = " + Calculator.Operation.MINUS.apply(pair.left, pair.right));
				System.out.println(pair.left + " / " + pair.right + " = " + Calculator.Operation.DIVIDE.apply(pair.left, pair.right));
				System.out.println(pair.left + " * " + pair.right + " = " + Calculator.Operation.MULTIPLY.apply(pair.left, pair.right));
			} catch (Throwable t) {
				t.printStackTrace();
			}
		});
	}
}

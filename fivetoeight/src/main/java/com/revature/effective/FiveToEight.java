package com.revature.effective;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.effective.enums.Calculator;

@SpringBootApplication
public class FiveToEight implements CommandLineRunner {
	
	@Autowired Calculator<? extends Number> calculator;
	
    public static void main(String[] args) {
        SpringApplication.run(FiveToEight.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		final List<ImmutablePair<Number, Number>> numbers = Arrays.asList(
			ImmutablePair.of((byte)3, (byte)6),
			ImmutablePair.of(Byte.valueOf((byte)3), Byte.valueOf((byte)6)),
			ImmutablePair.of((short)3, (short)6),
			ImmutablePair.of(Short.valueOf((short)3), Short.valueOf((short)6)),
			ImmutablePair.of(3, 6),
			ImmutablePair.of(Integer.valueOf(3), Integer.valueOf(6)),
			ImmutablePair.of(3L, 6L),
			ImmutablePair.of(Long.valueOf(3), Long.valueOf(6)),
			ImmutablePair.of(3.0d, 6.0d),
			ImmutablePair.of(Double.valueOf(3), Double.valueOf(6)),
			ImmutablePair.of(3.0f, 6.0f),
			ImmutablePair.of(Float.valueOf(3), Float.valueOf(6))
		);
		
		if (!numbers.isEmpty()) numbers.forEach(pair -> {
			try {
				System.out.println(String.format(Calculator.CALCULATOR_OPERATION, pair.getKey().getClass().toString().split(" ")[1]));
				System.out.println(pair.left + " + " + pair.right + " = " + Calculator.Operation.PLUS.apply(pair.left, pair.right));
				System.out.println(pair.left + " - " + pair.right + " = " + Calculator.Operation.MINUS.apply(pair.left, pair.right));
				System.out.println(pair.left + " / " + pair.right + " = " + Calculator.Operation.DIVIDE.apply(pair.left, pair.right));
				System.out.println(pair.left + " * " + pair.right + " = " + Calculator.Operation.MULTIPLY.apply(pair.left, pair.right));
				
				System.out.println(String.format(Calculator.CALCULATOR, pair.getKey().getClass().toString().split(" ")[1]));
				System.out.println(pair.left + " + " + pair.right + " = " + calculator.add(pair.left, pair.right));
				System.out.println(pair.left + " - " + pair.right + " = " + calculator.subtract(pair.left, pair.right));
				System.out.println(pair.left + " / " + pair.right + " = " + calculator.add(pair.left, pair.right));
				System.out.println(pair.left + " * " + pair.right + " = " + calculator.add(pair.left, pair.right));
				System.out.println();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		});
	}
}

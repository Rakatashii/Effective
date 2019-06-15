package com.revature.effective.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatingService {
	@Autowired GenericCalculator<? extends Number> calculatorInstance;
	
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
	public void calculate() {
		if (!numbers.isEmpty()) numbers.forEach(pair -> {
			try {
				
				System.out.println(String.format(GenericCalculator.CALCULATOR_OPERATION, pair.getKey().getClass().toString().split(" ")[1]));
				System.out.println(pair.left + GenericCalculator.Operation.PLUS.toString() + pair.right + 
						" = " + GenericCalculator.Operation.PLUS.apply(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.MINUS.toString() + pair.right + 
						" = " + GenericCalculator.Operation.MINUS.apply(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.DIVIDE.toString() + pair.right + 
						" = " + GenericCalculator.Operation.DIVIDE.apply(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.MULTIPLY.toString() + pair.right + 
						" = " + GenericCalculator.Operation.MULTIPLY.apply(pair.left, pair.right));
				
				System.out.println(String.format(GenericCalculator.CALCULATOR, pair.getKey().getClass().toString().split(" ")[1]));
				System.out.println(pair.left + GenericCalculator.Operation.PLUS.toString() + pair.right +
						" = " + calculatorInstance.add(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.MINUS.toString() + pair.right +
						" = " + calculatorInstance.subtract(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.DIVIDE.toString() + pair.right +
						" = " + calculatorInstance.divide(pair.left, pair.right));
				System.out.println(pair.left + GenericCalculator.Operation.MULTIPLY.toString() + pair.right +
						" = " + calculatorInstance.multiply(pair.left, pair.right));
				
				System.out.println();
				
			} catch (Throwable t) {
				t.printStackTrace();
			}
		});
	}
}

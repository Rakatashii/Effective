package com.revature.effective.calculate;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatingService {
	
	@Autowired Calculator<? extends Number> calculatorInstance;
	
	Set<Calculator.Operation> OPERATION_SET;
	Map<Calculator.Operation, String> innerBitMap = new HashMap<>();
	EnumMap<Calculator.Operation, String> OPERATION_MAP; 
	
	public void initializeVariables() {
		
		OPERATION_SET = EnumSet.allOf(Calculator.Operation.class);
		
		OPERATION_SET.addAll(Arrays.asList(Calculator.Operation.values()));

		OPERATION_SET.forEach(operation -> {
			innerBitMap.put(operation, operation.getSymbol());
		});
		
		OPERATION_MAP = new EnumMap<Calculator.Operation, String> (
			innerBitMap = OPERATION_SET.stream()
				.collect(Collectors.toMap(
						Function.identity(), 
						operation -> operation.getSymbol())));;
	}
	
	final static List<ImmutablePair<? extends Number, ? extends Number>> numbers = Arrays.asList(
			ImmutablePair.of((byte)3, 				  (byte)6),
			ImmutablePair.of(Byte.valueOf((byte)3),   Byte.valueOf((byte)6)),
			ImmutablePair.of((short)3, 				  (short)6),
			ImmutablePair.of(Short.valueOf((short)3), Short.valueOf((short)6)),
			ImmutablePair.of(3, 					  6),
			ImmutablePair.of(Integer.valueOf(3),      Integer.valueOf(6)),
			ImmutablePair.of(3L, 					  6L),
			ImmutablePair.of(Long.valueOf(3),         Long.valueOf(6)),
			ImmutablePair.of(3.0d, 					  6.0d),
			ImmutablePair.of(Double.valueOf(3),       Double.valueOf(6)),
			ImmutablePair.of(3.0f, 					  6.0f),
			ImmutablePair.of(Float.valueOf(3),        Float.valueOf(6))
	);
	
	public void printEnumMapDetails() {
		if (!OPERATION_MAP.isEmpty()) {
			System.out.println("\n Static CALCULATOR.OPERATION Operations:\n");
			OPERATION_MAP.entrySet().forEach(entry -> 
				System.out.println("\tOPERATION-" + (entry.getKey().ordinal()+1) 
					+ " - " + entry.getKey() + " : SYMBOL - " + entry.getValue()));
			System.out.println();
		}
	}
	
	public void calculateWithClass() {
		if (!numbers.isEmpty()) numbers.forEach(pair -> {	
			try {		
				System.out.println(String.format(Calculator.CALCULATOR_OPERATION, 
					((Class<? extends Number>) pair.left.getClass()).getName()));
				System.out.println(pair.left + Calculator.Operation.PLUS.getSymbol() + pair.right + 
					" = " + Calculator.Operation.PLUS.apply(pair.left, pair.right));
				System.out.println(pair.left + Calculator.Operation.MINUS.getSymbol() + pair.right + 
					" = " + Calculator.Operation.MINUS.apply(pair.left, pair.right));
				System.out.println(pair.left + Calculator.Operation.DIVIDE.getSymbol()  + pair.right + 
					" = " + Calculator.Operation.DIVIDE.apply(pair.left, pair.right));
				System.out.println(pair.left + Calculator.Operation.MULTIPLY.getSymbol() + pair.right + 
					" = " + Calculator.Operation.MULTIPLY.apply(pair.left, pair.right));
				System.out.println();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		});
	}
	
	public void calculateWithEnumSet() {
		if (!numbers.isEmpty()) numbers.forEach(pair -> {	
			System.out.println(String.format(Calculator.CALCULATOR, 
				pair.getKey().getClass().toString().split(" ")[1]));
			OPERATION_SET.forEach(operation -> {
				try {
					System.out.println(pair.left + operation.getSymbol() + pair.right +
						" = " + String.valueOf(operation.apply(pair.left, pair.right)));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			});
			System.out.println();
		});
	}
	
	public void calculateWithLambdas() {
		if (!numbers.isEmpty()) numbers.forEach(pair -> {
			System.out.println(String.format(CalculatorWithLambda.CALCULATOR_L, 
					pair.getKey().getClass().toString().split(" ")[1]));
			System.out.println(pair.left + CalculatorWithLambda.Operation.PLUS.getSymbol() + pair.right 
				+ " = " + CalculatorWithLambda.Operation.PLUS.apply(pair.left, pair.right) + "\n");
		});
	}
}

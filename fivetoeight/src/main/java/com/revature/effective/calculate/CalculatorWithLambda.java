package com.revature.effective.calculate;

import java.util.function.BinaryOperator;

public class CalculatorWithLambda {

	public final static String CALCULATOR_L		    = "---- CALCULATOR LAMBDA : TYPE - [%s]";
	
	public static enum Operation {
		PLUS(" + ", (x, y) -> (x + y)),
		MINUS(" - ", (x, y) -> (x - y)),
		DIVIDE(" / ", (x, y) -> (x / y)),
		MULTIPLY(" * ", (x, y) -> (x * y));
		
		BinaryOperator<Double> op;
		private final String symbol;
		
		Operation(String symbol, BinaryOperator<Double> op) { 
			this.symbol = symbol; 
			this.op = op;
		}
		
		public Number apply(Number x, Number y) {
			return op.apply(x.doubleValue(), y.doubleValue());
		}
		
		public String getSymbol() {
			return this.symbol;
		}
	}
}

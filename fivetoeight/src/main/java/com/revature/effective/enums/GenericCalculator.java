package com.revature.effective.enums;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericCalculator<T> { // Notice T is never used... Left here to highlight that

	public final static String CALCULATOR		    = "---- CALCULATOR METH OF TYPE [%s]";
	public final static String CALCULATOR_OPERATION = "----  OPERATION ENUM OF TYPE [%s]";
	
	public <N extends Number> Number add(N x, N y) {
		return Operation.PLUS.apply(x, y);
	}
	
	public <N extends Number> Number subtract(N x, N y) {
		return Operation.MINUS.apply(x, y);
	}
	
	public <N extends Number> Number divide(N x, N y) {
		return Operation.DIVIDE.apply(x, y);
	}
	
	public <N extends Number> Number multiply(N x, N y) {
		return Operation.MULTIPLY.apply(x, y);
	}
	
	public static enum Operation {
		PLUS(" + ") {
			@Override
			public <N extends Number> Number apply(N x, N y) {
				return x.doubleValue() + y.doubleValue();
			}
		},
		MINUS(" - ") {
			@Override
			public <N extends Number> Number apply(N x, N y) {
				return x.doubleValue() - y.doubleValue();
			}
			
		},
		DIVIDE(" / ") {
			@Override
			public <N extends Number> Number apply(N x, N y) {
				return x.doubleValue() / y.doubleValue();
			}
		},
		MULTIPLY(" * ") {
			@Override
			public <N extends Number> Number apply(N x, N y) {
				return x.doubleValue() * y.doubleValue();
			}
		};
		
		private final String symbol;
		
		Operation(String symbol) { 
			this.symbol = symbol; 
		}
		
		@Override public String toString() { 
			return symbol; 
		}
		
		public abstract <N extends Number> Number apply(N x, N y);
	}
}

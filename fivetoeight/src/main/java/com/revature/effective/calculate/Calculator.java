package com.revature.effective.calculate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Calculator<T> { // T is not used.. just highlighting that

	public final static String CALCULATOR		    = "---- CALCULATOR METH : TYPE - [%s]";
	public final static String CALCULATOR_OPERATION = "---- OPERATION ENUM  : TYPE - [%s]";
	
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
		
		public abstract <N extends Number> Number apply(N x, N y);

		
		public String getSymbol() {
			return this.symbol;
		}

	}
}

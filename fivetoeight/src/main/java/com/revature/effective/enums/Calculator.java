package com.revature.effective.enums;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Calculator<T extends Number> {

	public Number sum(T x, T y) {
		return Operation.PLUS.apply(x, y);
	}
	
	public static enum Operation {
		PLUS{
			@Override
			public <T extends Number> Number apply(T x, T y) {
				return x.doubleValue() + y.doubleValue();
			}
		},
		MINUS{
			@Override
			public <T extends Number> Number apply(T x, T y) {
				return x.doubleValue() - y.doubleValue();
			}
			
		},
		DIVIDE{
			@Override
			public <T extends Number> Number apply(T x, T y) {
				return x.doubleValue() / y.doubleValue();
			}
		},
		MULTIPLY{
			@Override
			public <T extends Number> Number apply(T x, T y) {
				return x.doubleValue() * y.doubleValue();
			}
		};
		
		public abstract <T extends Number> Number apply(T x, T y);

	}
}

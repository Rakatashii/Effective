package com.revature.effective.streams;

import java.util.function.BiFunction;
import java.util.function.LongFunction;

import org.springframework.stereotype.Component;

@Component
public class Functions {
	
	// Note: Function input type and output type should always differ. Otherwise, 
	// may as well be using a UnaryPredicate<Double>, for example - which has less overhead.
	
	LongFunction<Double> func = l -> l * Math.PI;
	BiFunction<Long, Long, Double> biFunc = (x, y) -> ((x * Math.PI) / y);
	
	Double applyFunc(long l) {
		return func.apply(l);
	}
	
	Double applyBiFunc(Long x, Long y) {
		return biFunc.apply(x, y);
	}
}

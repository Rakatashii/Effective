package com.revature.effective;

import lombok.NoArgsConstructor;

// 1-3 Singleton enforced with Enum (or private static)
@NoArgsConstructor
public class Singleton {
	private static final Singleton INSTANCE = new Singleton();
	
	
}

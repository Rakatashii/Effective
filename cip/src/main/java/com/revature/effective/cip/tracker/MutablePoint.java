package com.revature.effective.cip.tracker;

import com.revature.effective.cip.annotation.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {
	public int x, y;
	
	public MutablePoint() {
		x = 0;
		y = 0;
	}
	
	// copy-constructor
	public MutablePoint(MutablePoint p) {
		this.x = p.x;
		this.y = p.y;
	}
}

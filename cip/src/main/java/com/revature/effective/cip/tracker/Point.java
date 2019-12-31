package com.revature.effective.cip.tracker;

import com.revature.effective.cip.annotation.Immutable;

@Immutable
public class Point {
	public final int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

package com.revature.effective.cip.tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.revature.effective.cip.annotation.ThreadSafe;

@ThreadSafe
public class DelegatingVehicleTracker {
	private final ConcurrentMap<String, Point> locations;
	private final Map<String, Point> unmodifiableMap;
	
	public DelegatingVehicleTracker(Map<String, Point> points) {
		locations = new ConcurrentHashMap<String, Point>(points);
		unmodifiableMap = Collections.unmodifiableMap(locations);
	}
	
	// Returning a 'live' copy of locations
	public Map<String, Point> getLocations(){
		return unmodifiableMap;
	}
	
	public Point getLocation(String id) {
		return locations.get(id);
	}
	
	public void setLocation(String id, int x, int y) {
		if (locations.replace(id, new Point(x, y)) == null) {
			throw new IllegalArgumentException("invalid vehicle id - " + id);
		}
	}
	
	// Now returning a static copy of the location set, rather than a 'live' one
	public Map<String, Point> getStaticLocations(){
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
	}
}

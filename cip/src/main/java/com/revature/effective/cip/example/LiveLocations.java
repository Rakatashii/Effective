package com.revature.effective.cip.example;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LiveLocations {

	public ConcurrentMap<String,String> locations;
	public Map<String,String> view;
	
	public LiveLocations(Map<String,String> locations) {
		this.locations = new ConcurrentHashMap<String,String>(locations);
		view = Collections.unmodifiableMap(locations);
	}
	
	public Map<String,String> getLocations() {
		return this.view;
	}
	
	// Live rendering of locations
	public void setLocation(String key, String val) {
		if (locations.replace(key,val) == null){ // only null if key doesn't exist
			throw new IllegalArgumentException("Location does not exist.");
		}
	}
	
	// Static snapshot of locations
	public Map<String,String> getLocationsSnapshot(){
		return Collections.unmodifiableMap(this.locations);
	}
}

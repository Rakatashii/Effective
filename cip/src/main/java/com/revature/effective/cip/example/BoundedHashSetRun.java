package com.revature.effective.cip.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.effective.cip.sample.Book;
import com.revature.effective.cip.synchronizers.BoundedHashSet;

public class BoundedHashSetRun {
	
	private static final Logger log = LoggerFactory.getLogger(BoundedHashSetRun.class);

	public void run() {
		
		BoundedHashSet<Book> books = new BoundedHashSet<>(5);
		
		log.info("Adding A - {}", books.addNormally(new Book("A", "?", 10.0)));
		log.info("Adding B - {}", books.addNormally(new Book("B", "?", 10.0)));
		log.info("Adding C - {}", books.addNormally(new Book("C", "?", 10.0)));
		
		Book d = new Book("D", "?", 10.0);
		log.info("Adding D - {}", books.addNormally(d));
		
		Book e = new Book("E", "?", 10.0);
		log.info("Adding E - {}", books.addNormally(e));
		log.info("Removing E - {}", books.removeNormally(e));
		
		try {
			Book f = new Book("F", "?", 10.0);
			log.info("Adding F - {}", books.addNormally(f));
			
			try {
				log.info("Removing F - {}", books.removeNormally(f));
			} catch (Throwable removeError) {
				log.error("Failed to remove F : Error - ", removeError);
			}
			
		} catch (Throwable addError) {
			log.error("Failed to add F : Error - ", addError);
		} finally {
			log.info("Removing D - {}", books.removeNormally(d));
		}
	}
}

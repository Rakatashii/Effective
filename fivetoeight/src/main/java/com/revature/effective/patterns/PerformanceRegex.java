package com.revature.effective.patterns;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerformanceRegex implements CommandLineRunner {
	public static final Pattern QR_CODE_TX_REGEX = Pattern.compile("(?i)^http.*$");
	public static final Pattern PHARMACY_TX_REGEX = Pattern.compile("^G.*/.*$");
	public static final Pattern UPC_TX_REGEX = Pattern.compile("^\\d{0,12}$");
	
	public static void main(String[] args) {
		SpringApplication.run(PerformanceRegex.class, args);
	}
	
	static final List<String> QR_CODE_TX_Strings = Arrays.asList(
		"http123123923io", // yes
		"HTTP239923ksjdkjs", // yes 
		"09httpksdjbcn23", // no 
		"00oHTTPo2983iojnd", // no
		"sjdklbcnsjkdbcnHTTP", // no
		"kjsdhttpkjsdnbskjdn" // no
		// good
	);
	static final List<String> PHARMACY_TX_Strings = Arrays.asList(
		"Gskdjln/sldkjn/", // yes
		"Gskdjlnsldkjn/", // yes
		"gskdjln/sldkjn/", // no
		"Gskdjlnsldkjn/", // yes
		"Gsskdjlnsldkjn", // no
		"Gsskdjl//nsldkjn" // yes
		// good
	);
	static final List<String> UPC_TX_Strings = Arrays.asList(
		"123234345456", //12 d yes
		"12323434556", //11 d yes
		"1232353455643", //13 d no
		"21312312312s", // 12 ds no
		"p2132342355", // 11 ds no
		"12312312l1232" // 13 ds no
		// good
	);

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		QR_CODE_TX_Strings.forEach(qr -> {
			if (QR_CODE_TX_REGEX.matcher(qr).matches()) {
				System.out.println(qr + " LOOKS LIKE A QR CODE");
			} else {
				System.out.println(qr + " DOES NOT LOOK LIKE A QR CODE");
			}
		});
		System.out.println();
		PHARMACY_TX_Strings.forEach(qr -> {
			if (PHARMACY_TX_REGEX.matcher(qr).matches()) {
				System.out.println(qr + " LOOKS LIKE A PHARMACY TX");
			} else {
				System.out.println(qr + " DOES NOT LOOK LIKE A PHARMACY TX");
			}
		});
		System.out.println();
		UPC_TX_Strings.forEach(qr -> {
			if (UPC_TX_REGEX.matcher(qr).matches()) {
				System.out.println(qr + " LOOKS LIKE A UPC TX");
			} else {
				System.out.println(qr + " DOES NOT LOOK LIKE A UPC TX");
			}
		});
		System.out.println();
	}
}

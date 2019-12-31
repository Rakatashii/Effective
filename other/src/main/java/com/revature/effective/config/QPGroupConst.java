package com.revature.effective.config;

import org.springframework.http.HttpHeaders;

public class QPGroupConst {
	
	public static final int CONCURRENCY = 4;
	
	public static final Integer CONNECT_TIMEOUT_MILLIS = 12000;
	public static final int READ_TIMEOUT_SECONDS = 12;
	public static final int WRITE_TIMEOUT_SECONDS = 12;
	
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String RESOURCE = "resource";
	public static final String GRANT_TYPE = "grant_type";

	public static final String LOOKUP_URI = "/score/order/v1/getOrderDetails/order/{orderId}";

	public static final String HTTPS = "https";
	
	public static final String QP_CLUB_HEADER = "X-EBU-Nbr";
	
	public static final String OAUTH_KEY = HttpHeaders.AUTHORIZATION;
	public static final long OAUTH_REFRESH_RATE = 3550000L; /* 55 Minutes */
	
	public static final String CLUB_NBR = "clubNbr";
	public static final String ORDER_ID = "orderId";
	
}

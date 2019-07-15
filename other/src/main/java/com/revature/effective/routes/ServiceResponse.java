package com.revature.effective.routes;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ServiceResponse<T> {
	
	private Status status = Status.SUCCESS;
	
	private String errorMessage;
	
	private T payload;
	
	public ServiceResponse(T payload){
		this.payload = payload;
	}

	public ServiceResponse(String errorMessage) {
		this.status = Status.ERROR;
		this.errorMessage = errorMessage;
	}
	
	enum Status {
		
		SUCCESS(200),ERROR(500),NOT_FOUND(404);

		private int responseCode = 0;
		
		Status(int response) {
			this.responseCode = response;
		}

		public int getResponseCode() {
			return responseCode;
		}
	}
}
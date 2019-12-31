package com.revature.effective.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoVO {
	
	private String membershipNbr;
	private String customerFirstName;
	private String customerLastName;
	
}

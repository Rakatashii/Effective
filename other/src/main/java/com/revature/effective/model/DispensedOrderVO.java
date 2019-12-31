package com.revature.effective.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DispensedOrderVO {

	private String orderId;
	private Integer fulfillEbuNbr;
	private String customerOrderId;
	private String prepaidInd;

	private CustomerInfoVO customerInfo;
	private List<DispenseDetailsVO> dispenseDetails;

}

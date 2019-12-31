package com.revature.effective.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DispenseItemVO {

	private Integer orderItemNbr;
	private String dispenseGtin;
	private String itemDesc1;
	private Integer dispenseQty;
    private Double dispenseExtRetailAmt;
	
}

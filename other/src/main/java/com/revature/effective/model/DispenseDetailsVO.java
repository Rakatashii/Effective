package com.revature.effective.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DispenseDetailsVO {

	private String dispensePrepaidInd;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss XXX", timezone="UTC")
	private ZonedDateTime dispenseTs;
	
	private String dispenseToType;
	private String dispenseToName;
	private String dispenseByName;

	private List<DispenseItemVO> dispenseItems;

}

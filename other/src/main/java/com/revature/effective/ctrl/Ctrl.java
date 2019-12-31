package com.revature.effective.ctrl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.effective.config.WebClientConfig;
import com.revature.effective.model.DispensedOrderVO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mock")
public class Ctrl {
	
	@Autowired WebClientConfig webClientConfig;
	
	private static final Logger log = LoggerFactory.getLogger(Ctrl.class);
	
	@Autowired 
	private ObjectMapper mapper;

	@GetMapping("/getDispensedOrder/{orderId}")
	private Mono<DispensedOrderVO> getDispensedOrder(@PathVariable String orderId) {

		return getDispensedOrderFromString();
	}
	
	private Mono<DispensedOrderVO> getDispensedOrderFromString() {
		
		DispensedOrderVO dispensedOrder = null;
		
		String raw = 
				"{\n" + 
				"    \"orderId\": 2339,\n" + 
				"    \"countryCd\": \"US\",\n" + 
				"    \"fulfillEbuNbr\": 4808,\n" + 
				"    \"orderStatus\": \"DISP-COMPLETE\",\n" + 
				"    \"customerOrderId\": \"3280823652\",\n" + 
				"    \"fulfillOrderId\": \"001\",\n" + 
				"    \"orderType\": \"PROD\",\n" + 
				"    \"programType\": \"CPU\",\n" + 
				"    \"prepaidInd\": \"Y\",\n" + 
				"    \"customerInfo\": {\n" + 
				"        \"membershipNbr\": \"1011323678912345\",\n" + 
				"        \"customerFirstName\": \"MICKEY\",\n" + 
				"        \"customerLastName\": \"MOUSE\"\n" + 
				"    },\n" + 
				"    \"orderLineSummary\": {\n" + 
				"        \"orderLineCnt\": 6,\n" + 
				"        \"totalDispensedQty\": 3\n" + 
				"    },\n" + 
				"    \"dispenseDetails\": [\n" + 
				"        {\n" + 
				"            \"dispenseSeqNbr\": 1,\n" + 
				"            \"dispensePrepaidInd\": \"Y\",\n" + 
				"            \"dispenseTs\": \"2019-08-11 11:38:29 -05:00\",\n" + 
				"            \"dispenseToType\": \"MEMBER\",\n" + 
				"            \"dispenseToName\": \"MICKEY MOUSE\",\n" + 
				"            \"dispenseById\": 1000,\n" + 
				"            \"dispenseByInit\": \"RA\",\n" + 
				"            \"dispenseByName\": \"RANDOM ASSOCIATE\",\n" + 
				"            \"dispenseItems\": [\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 1,\n" + 
				"                    \"itemSeqNbr\": -2,\n" + 
				"                    \"orderItemNbr\": 980088353,\n" + 
				"                    \"itemDesc1\": \"BULK NYCHEESECAKE\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"OZ\",\n" + 
				"                    \"unitRetailAmt\": 12.98,\n" + 
				"                    \"dispenseGtin\": \"00229211712988\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 1,\n" + 
				"                    \"dispenseExtRetailAmt\": 12.98\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 1,\n" + 
				"                    \"itemSeqNbr\": -1,\n" + 
				"                    \"orderItemNbr\": 980088353,\n" + 
				"                    \"itemDesc1\": \"BULK NYCHEESECAKE\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"OZ\",\n" + 
				"                    \"unitRetailAmt\": 12.98,\n" + 
				"                    \"dispenseGtin\": \"00229211712988\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 1,\n" + 
				"                    \"dispenseExtRetailAmt\": 12.98\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 2,\n" + 
				"                    \"itemSeqNbr\": -2,\n" + 
				"                    \"orderItemNbr\": 847851,\n" + 
				"                    \"itemDesc1\": \"9CT ASSORTED MUFFINS\",\n" + 
				"                    \"sellByWeightInd\": \"N\",\n" + 
				"                    \"sellUom\": \"EA\",\n" + 
				"                    \"unitRetailAmt\": 5.98,\n" + 
				"                    \"dispenseGtin\": \"00228666605982\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 0,\n" + 
				"                    \"dispenseExtRetailAmt\": 5.98\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 2,\n" + 
				"                    \"itemSeqNbr\": -1,\n" + 
				"                    \"orderItemNbr\": 847851,\n" + 
				"                    \"itemDesc1\": \"9CT ASSORTED MUFFINS\",\n" + 
				"                    \"sellByWeightInd\": \"N\",\n" + 
				"                    \"sellUom\": \"EA\",\n" + 
				"                    \"unitRetailAmt\": 5.98,\n" + 
				"                    \"dispenseGtin\": \"00228666605982\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 0,\n" + 
				"                    \"dispenseExtRetailAmt\": 5.98\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 3,\n" + 
				"                    \"itemSeqNbr\": -5,\n" + 
				"                    \"orderItemNbr\": 483367,\n" + 
				"                    \"itemDesc1\": \"ABF CHICKEN BREAST\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 3.48,\n" + 
				"                    \"dispenseGtin\": \"00213525015170\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 4.36,\n" + 
				"                    \"dispenseExtRetailAmt\": 15.17\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 3,\n" + 
				"                    \"itemSeqNbr\": -4,\n" + 
				"                    \"orderItemNbr\": 483367,\n" + 
				"                    \"itemDesc1\": \"ABF CHICKEN BREAST\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 3.48,\n" + 
				"                    \"dispenseGtin\": \"00213525016849\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 4.84,\n" + 
				"                    \"dispenseExtRetailAmt\": 16.84\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 3,\n" + 
				"                    \"itemSeqNbr\": -3,\n" + 
				"                    \"orderItemNbr\": 483367,\n" + 
				"                    \"itemDesc1\": \"ABF CHICKEN BREAST\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 3.48,\n" + 
				"                    \"dispenseGtin\": \"00213525015491\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 4.45,\n" + 
				"                    \"dispenseExtRetailAmt\": 15.49\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 3,\n" + 
				"                    \"itemSeqNbr\": -2,\n" + 
				"                    \"orderItemNbr\": 483367,\n" + 
				"                    \"itemDesc1\": \"ABF CHICKEN BREAST\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 3.48,\n" + 
				"                    \"dispenseGtin\": \"00213525015170\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 4.36,\n" + 
				"                    \"dispenseExtRetailAmt\": 15.17\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 3,\n" + 
				"                    \"itemSeqNbr\": -1,\n" + 
				"                    \"orderItemNbr\": 483367,\n" + 
				"                    \"itemDesc1\": \"ABF CHICKEN BREAST\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 3.48,\n" + 
				"                    \"dispenseGtin\": \"00213525017129\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 4.92,\n" + 
				"                    \"dispenseExtRetailAmt\": 17.12\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 4,\n" + 
				"                    \"itemSeqNbr\": -5,\n" + 
				"                    \"orderItemNbr\": 323576,\n" + 
				"                    \"itemDesc1\": \"BNLS PORK LOIN CHOPS\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 2.48,\n" + 
				"                    \"dispenseGtin\": \"00211003806456\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 2.6,\n" + 
				"                    \"dispenseExtRetailAmt\": 6.45\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 4,\n" + 
				"                    \"itemSeqNbr\": -2,\n" + 
				"                    \"orderItemNbr\": 323576,\n" + 
				"                    \"itemDesc1\": \"BNLS PORK LOIN CHOPS\",\n" + 
				"                    \"sellByWeightInd\": \"Y\",\n" + 
				"                    \"sellUom\": \"LB\",\n" + 
				"                    \"unitRetailAmt\": 2.48,\n" + 
				"                    \"dispenseGtin\": \"00211003806456\",\n" + 
				"                    \"dispenseQty\": 1,\n" + 
				"                    \"dispensePackageWeight\": 2.6,\n" + 
				"                    \"dispenseExtRetailAmt\": 6.45\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 7,\n" + 
				"                    \"itemSeqNbr\": 1,\n" + 
				"                    \"orderItemNbr\": 279441,\n" + 
				"                    \"itemDesc1\": \"DURACELL AA 48PK\",\n" + 
				"                    \"sellByWeightInd\": \"N\",\n" + 
				"                    \"sellUom\": \"EA\",\n" + 
				"                    \"unitRetailAmt\": 21.98,\n" + 
				"                    \"dispenseGtin\": \"00041333663791\",\n" + 
				"                    \"dispenseQty\": 2,\n" + 
				"                    \"dispensePackageWeight\": 0,\n" + 
				"                    \"dispenseExtRetailAmt\": 43.96\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    \"orderLineNbr\": 9,\n" + 
				"                    \"itemSeqNbr\": 28,\n" + 
				"                    \"orderItemNbr\": 749972,\n" + 
				"                    \"itemDesc1\": \"STRAWBERRIES 2LB\",\n" + 
				"                    \"sellByWeightInd\": \"N\",\n" + 
				"                    \"sellUom\": \"EA\",\n" + 
				"                    \"unitRetailAmt\": 2.98,\n" + 
				"                    \"dispenseGtin\": \"00715756200061\",\n" + 
				"                    \"dispenseQty\": 3,\n" + 
				"                    \"dispensePackageWeight\": 0,\n" + 
				"                    \"dispenseExtRetailAmt\": 8.94\n" + 
				"                }\n" + 
				"            ]\n" + 
				"        }\n" + 
				"    ]\n" + 
				"}" ;
		
		try {
			dispensedOrder = mapper.readValue(raw.getBytes(), DispensedOrderVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return Mono.error(e);
		}
		
		log.info("dispensedOrder - \n{}", dispensedOrder);
		return Mono.just(dispensedOrder);
	}
}

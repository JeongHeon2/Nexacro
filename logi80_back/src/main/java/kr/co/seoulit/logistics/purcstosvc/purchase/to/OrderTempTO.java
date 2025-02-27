package kr.co.seoulit.logistics.purcstosvc.purchase.to;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

import jakarta.persistence.Table;

@Data
@Table(name="ORDER_REQUIRED_TEMP")
@Dataset(name="gds_orderTemp")
public class OrderTempTO {

	private String mrpGatheringNo;
	private String itemCode;
	private String itemName;
	private String unitOfMrp;
	private int requiredAmount;
	private int stockAmount;
	private String orderDate;
	private String requiredDate;
	private String checked;

}

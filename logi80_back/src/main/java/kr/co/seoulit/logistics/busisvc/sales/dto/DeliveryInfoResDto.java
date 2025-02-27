package kr.co.seoulit.logistics.busisvc.sales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="gds_deliveryInfo")
public class DeliveryInfoResDto extends BaseTO implements Serializable { // redis 적용 => implements Serializable

	private String deliveryNo;
	private String estimateNo;
	private String contractNo;
	private String contractDetailNo;
	private String customerCode;
	private String personCodeInCharge;
	private String itemCode;
	private String itemName;
	private String unitOfDelivery;
	private String deliveryAmount;
	private String unitPrice;
	private String sumPrice;
	private String deliveryDate;
	private String deliveryPlaceName;
}

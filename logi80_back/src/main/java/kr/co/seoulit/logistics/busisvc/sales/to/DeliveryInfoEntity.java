package kr.co.seoulit.logistics.busisvc.sales.to;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="DELIVERY_INFO")
@Dataset(name="gds_deliveryInfo")
public class DeliveryInfoEntity extends BaseTO implements Serializable {
	
	@Id
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

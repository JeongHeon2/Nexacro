package kr.co.seoulit.logistics.purcstosvc.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="STOCK")
@Dataset(name="gds_stock")
@EqualsAndHashCode(callSuper = false)
public class StockEntity extends BaseTO {

	private String warehouseCode;
	@Id
	private String itemCode;
	private String itemName;
	private String unitOfStock;
	private String safetyAllowanceAmount;
	private String stockAmount;
	private String orderAmount;
	private String inputAmount;
	private String deliveryAmount;
	private String releaseAmount;
	private String totalStockAmount;

	@Transient
	private String checked;
}

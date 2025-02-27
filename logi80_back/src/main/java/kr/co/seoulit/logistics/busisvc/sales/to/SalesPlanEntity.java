package kr.co.seoulit.logistics.busisvc.sales.to;

import jakarta.persistence.Table;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SALES_PLAN")
@Dataset(name="gds_salesPlan")
public class SalesPlanEntity extends BaseTO {
	@Id
	private String salesPlanNo;
	private String itemCode;
	private String itemName;
	private String unitOfSales;
	private String salesPlanDate;
	private String dueDateOfSales;
	private int salesAmount;
	private int unitPriceOfSales;
	private int sumPriceOfSales;
	private String mpsApplyStatus;
	private String description;
}

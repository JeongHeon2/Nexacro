package kr.co.seoulit.logistics.busisvc.sales.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="gds_salesPlan")
public class SalesPlanTO extends BaseTO {
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
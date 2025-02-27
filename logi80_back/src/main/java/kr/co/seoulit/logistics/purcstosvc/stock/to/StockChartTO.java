package kr.co.seoulit.logistics.purcstosvc.stock.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Dataset(name="stockchart")
@Data
@EqualsAndHashCode(callSuper = false)
public class StockChartTO extends BaseTO {
	
	private String itemName;
	private String stockAmount;
	private String saftyAmount;
	private String allowanceAmount;

}

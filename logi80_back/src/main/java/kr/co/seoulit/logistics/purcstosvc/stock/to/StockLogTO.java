package kr.co.seoulit.logistics.purcstosvc.stock.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Dataset(name="gds_stockLog")
@EqualsAndHashCode(callSuper = false)
public class StockLogTO extends BaseTO implements Serializable {
	
	private String logDate;
	private String itemCode;
	private String itemName;
	private String amount;
	private String reason;
	private String cause;
	private String effect;

}

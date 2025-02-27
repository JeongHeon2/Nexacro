package kr.co.seoulit.logistics.purcstosvc.stock.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Dataset(name ="gds_bomInfo")
@EqualsAndHashCode(callSuper = false)
public class BomInfoTO extends BaseTO {
	
	private String itemCode;
	private String parentItemCode;
	private int no;
	private String itemName;
	private String itemClassification;
	private String itemClassificationName;
	private int netAmount;
	private String description;

}

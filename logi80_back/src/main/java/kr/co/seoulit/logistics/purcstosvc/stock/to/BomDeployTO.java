package kr.co.seoulit.logistics.purcstosvc.stock.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Dataset(name="gds_bomDeploy")
@EqualsAndHashCode(callSuper = false)
public class BomDeployTO extends BaseTO {
	
	private String bomNo;
	private int bomLevel;
	private String parentItemCode;
	private String itemCode;
	private String itemName;
	private String unitOfStock;
	private int netAmount;
	private String lossRate;
	private String necessaryAmount;	
	private String leadTime;
	private String isLeaf;
	private String description;

}

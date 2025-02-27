package kr.co.seoulit.logistics.purcstosvc.purchase.to;



import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

@Data
@Dataset(name="gds_orderInfo")
public class OrderInfoTO {

	private String orderNo;
	private String orderDate;
	private String orderInfoStatus;
	private String orderSort;
	private String itemCode;
	private String itemName;
	private String orderAmount;
	private String inspectionStatus;

	//체크 상태 그리드
	private String checked;
}

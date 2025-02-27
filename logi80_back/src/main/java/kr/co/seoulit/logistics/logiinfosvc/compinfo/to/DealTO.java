package kr.co.seoulit.logistics.logiinfosvc.compinfo.to;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="ds_deal")
public class DealTO extends BaseTO {
	String dealCode,dealDay,separation,collectMoney,paymentMoney,priceStatus;
}

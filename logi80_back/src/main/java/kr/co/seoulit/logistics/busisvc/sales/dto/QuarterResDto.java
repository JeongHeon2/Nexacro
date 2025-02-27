package kr.co.seoulit.logistics.busisvc.sales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="gds_quarter")
public class QuarterResDto extends BaseTO{
    private String qua;
    private String price = "0";
    private String reverse = "0";
    private String totalPrice = "0";
}

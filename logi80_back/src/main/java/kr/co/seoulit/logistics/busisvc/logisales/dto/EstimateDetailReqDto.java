package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name = "gds_estimateDetail")
public class EstimateDetailReqDto extends BaseTO implements Serializable {
    private String checked;
    private String estimateDetailNo;
    private String estimateNo;
    private String itemCode;
    private String itemName;
    private String unitOfEstimate;
    private String dueDateOfEstimate;
    private int estimateAmount;
    private int unitPriceOfEstimate;
    private int sumPriceOfEstimate;
    private String description;
}

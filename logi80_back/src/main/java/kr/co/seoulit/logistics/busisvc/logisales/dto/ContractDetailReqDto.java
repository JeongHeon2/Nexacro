package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="gds_contractDetail")
public class ContractDetailReqDto extends BaseTO {
    private String checked;
    private String contractDetailNo;
    private String contractNo;
    private String itemCode;
    private String itemName;
    private String unitOfContract;
    private String dueDateOfContract;
    private String estimateAmount;
    private String stockAmountUse;
    private String productionRequirement;
    private String unitPriceOfContract;
    private String sumPriceOfContract;
    private String processingStatus;
    private String operationCompletedStatus;
    private String deliveryCompletionStatus;
    private String releaseCompletionStatus;
    private String description;
}

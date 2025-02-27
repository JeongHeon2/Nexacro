package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContractInfoReqDto extends BaseTO {
    private String searchCondition;
    private String startDate;
    private String endDate;

    private String contractNo;
    private String estimateNo;
    private String contractType;
    private String contractTypeName;
    private String customerCode;
    private String customerName;
    private String estimateDate;
    private String contractDate;
    private String contractRequester;
    private String personCodeInCharge;
    private String empNameInCharge;
    private String description;
    private String deliveryCompletionStatus;
    private String releaseCompletionStatus;

    @RemoveColumn
    private ArrayList<ContractDetailReqDto> contractDetailReqDtoList;

}

package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name="gds_contract")
public class ContractResDto extends BaseTO {
    private String checked;
    private String contractNo;
    private String estimateNo;
    private String contractType;
    private String customerCode;
    private String contractDate;
    private String contractRequester;
    private String personCodeInCharge;
    private String description;
    private String deliveryCompletionStatus;
    private String releaseCompletionStatus;
    private ArrayList<ContractDetailResDto> contractDetailResDtoList;
}

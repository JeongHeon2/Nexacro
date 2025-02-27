package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name = "gds_estimate")
public class EstimateReqDto extends BaseTO implements Serializable{
    private String dateSearchCondition;
    private String startDate;
    private String endDate;
    private String estimateNo;
    private String customerCode;
    private String estimateDate;
    private String contractStatus;
    private String estimateRequester;
    private String effectiveDate;
    private String personCodeInCharge;
    private String description;
    private List<EstimateDetailReqDto> estimateDetailReqDtoList;
}

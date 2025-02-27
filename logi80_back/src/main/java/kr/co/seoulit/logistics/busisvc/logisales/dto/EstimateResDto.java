package kr.co.seoulit.logistics.busisvc.logisales.dto;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Dataset(name = "gds_estimate")
public class EstimateResDto  {
    private String estimateNo;
    private String customerCode;
    private String estimateDate;
    private String contractStatus;
    private String estimateRequester;
    private String effectiveDate;
    private String personCodeInCharge;
    private String description;
    private ArrayList<EstimateDetailResDto> estimateDetailResDtoList;
}

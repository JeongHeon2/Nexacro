package kr.co.seoulit.logistics.busisvc.logisales.dto;

import jakarta.persistence.Table;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;

import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "CONTRACT_LIST")
@Dataset(name="gds_contractInfo")
public class ContractInfoResDto extends BaseTO {
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
    private String deliveryCompletionStatus;
    private String releaseCompletionStatus;
    private String description;
    private String DETAIL_CODE_NAME;
    private String CONTRACT_DATE;
    private String DETAIL_CODE;
    @Transient
	private String checked;

    @RemoveColumn
    private ArrayList<ContractDetailResDto> contractDetailResDtoList;

}

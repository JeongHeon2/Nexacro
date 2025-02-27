package kr.co.seoulit.logistics.busisvc.logisales.dto;

import jakarta.persistence.Table;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "CONTRACT_LIST")
@Dataset(name="gds_contractInfo")
public class ContractInfoDto extends BaseTO {


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

	@RemoveColumn
	private ArrayList<ContractDetailEntity> contractDetailEntityList;
	private String deliveryCompletionStatus;
	private String releaseCompletionStatus;

}

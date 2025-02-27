package kr.co.seoulit.logistics.busisvc.logisales.entity;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="ESTIMATE_DETAIL")
@Dataset(name="gds_estimateDetail")
public class EstimateDetailEntity extends BaseTO implements Serializable{
	@Id
	private String estimateDetailNo;
	private String unitOfEstimate;
	private String estimateNo;
	private int unitPriceOfEstimate;
	private int sumPriceOfEstimate;
	private String description;
	private String itemCode;
	private int estimateAmount;
	private String dueDateOfEstimate;
	private String itemName;
	@Transient
	private String checked;

	@RemoveColumn
	@Transient
	private String status;


}
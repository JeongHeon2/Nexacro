package kr.co.seoulit.logistics.prodcsvc.production.to;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="MRP_GATHERING")
@Dataset(name="gds_mrpGathering")
public class MrpGatheringTO extends BaseTO {
	@Id
	private String mrpGatheringNo;
	private String orderOrProductionStatus;
	private String itemCode;
	private String itemName;
	private String unitOfMrpGathering;
	private String claimDate;
	private String dueDate;
	private int necessaryAmount;
	private int mrpGatheringSeq;
	
	@Transient
	private ArrayList<MrpTO> mrpTOList;

}
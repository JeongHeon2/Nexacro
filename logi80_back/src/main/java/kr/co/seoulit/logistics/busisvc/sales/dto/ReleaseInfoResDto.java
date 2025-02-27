package kr.co.seoulit.logistics.busisvc.sales.dto;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Transient;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="gds_releaseInfo")
public class ReleaseInfoResDto extends BaseTO implements Serializable { // redis 적용 => implements Serializable

    private String releaseNo;
    private String contractDetailNo;
    private String customerName;
    private String itemCode;
    private String itemName;
    private String registerDate;
    private String dueDate;
    private String plannedReleaseDate;
    private String unitOfStock;
    private String releaseAmount;
    private String personInCharge;
    private String warehouseCode;
    private String inspectionStatus;
    private String vehicleSelected;
	@Transient
	private String checked;
}

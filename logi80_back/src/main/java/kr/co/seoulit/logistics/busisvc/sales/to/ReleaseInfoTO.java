package kr.co.seoulit.logistics.busisvc.sales.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="RELEASE")
@Dataset(name="gds_releaseInfo")
public class ReleaseInfoTO extends BaseTO {

    @Id
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

	@Transient
	private String checked;
}

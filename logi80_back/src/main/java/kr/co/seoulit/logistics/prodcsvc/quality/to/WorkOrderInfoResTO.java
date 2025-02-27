package kr.co.seoulit.logistics.prodcsvc.quality.to;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

import jakarta.persistence.Transient;
import java.io.Serializable;


@Data
@Dataset(name="gds_workOrderInfo")
public class WorkOrderInfoResTO implements Serializable {

    private String workOrderNo;
    private String mrpNo;
    private String mrpGatheringNo;
    private String itemClassification;
    private String itemCode;
    private String itemName;
    private String unitOfMrp;
    private String requiredAmount;
    private String workSiteCode;
    private String workSiteName;
    private String productionProcessCode;
    private String productionProcessName;
    private String inspectionStatus;
    private String productionStatus;
    private String completionStatus;

    @Transient
    private String checked;
    @Transient
    private String actualCompletionAmount;


}

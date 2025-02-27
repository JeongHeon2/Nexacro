package kr.co.seoulit.logistics.prodcsvc.quality.Entity;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Data
@Entity
@Table(name="WORK_ORDER_INFO")
public class WorkOrderInfoEntity {

    @Id
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

    private String operationCompleted;   //처음에는 null

    @Transient
    private String checked;
    @Transient
    private String actualCompletionAmount;

}

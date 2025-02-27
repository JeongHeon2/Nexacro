package kr.co.seoulit.logistics.prodcsvc.production.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name = "gds_mps")
public class MpsResTO extends BaseTO implements Serializable {

    private String mpsNo;
    private String mpsPlanClassification;
    private String contractDetailNo;
    private String salesPlanNo;
    private String itemCode;
    private String itemName;
    private String unitOfMps; // 단위
    private String mpsPlanDate; // 계획일자
    private String mpsPlanAmount; 
    private String dueDateOfMps; // 마감일자
    private String scheduledEndDate;
    private String mrpApplyStatus;
    private String description;
    private String operationCompleted;
    private String checked;

}

package kr.co.seoulit.logistics.prodcsvc.production.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class MpsReqTO implements Serializable {

    private String startDate;
    private String endDate;
    private String includeMrpApply;
}

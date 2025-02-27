package kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto;


import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name="gds_itemGroup")
public class ItemGroupReqDto extends BaseTO {

    private String itemGroupCode;
    private String description;
    private String itemGroupName;

	@Transient
	private String checked;
}

package kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper=false)
@Dataset(name="ds_itemGroup")
public class ItemGroupResDto implements Serializable {
    private String itemGroupCode;
    private String description;
    private String itemGroupName;
}

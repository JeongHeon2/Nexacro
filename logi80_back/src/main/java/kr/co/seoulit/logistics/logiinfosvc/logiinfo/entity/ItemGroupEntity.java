package kr.co.seoulit.logistics.logiinfosvc.logiinfo.entity;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;

import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="ITEM_GROUP")
@Dataset(name="ds_itemGroup")
public class ItemGroupEntity extends BaseTO {
	 @Id
	 private String itemGroupCode;
	 private String description;
	 private String itemGroupName;


	@Transient
	private String checked;

	@Transient
	private String status;
}

package kr.co.seoulit.logistics.logiinfosvc.logiinfo.to;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="ITEM_GROUP")
@Dataset(name="ds_itemGroup")
public class ItemGroupTO {
	 @Id
	 private String itemGroupCode;
	 private String description;
	 private String itemGroupName;
	 
	@Transient
	private String checked;
}

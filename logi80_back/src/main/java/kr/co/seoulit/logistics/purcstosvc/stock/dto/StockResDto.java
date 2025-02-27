package kr.co.seoulit.logistics.purcstosvc.stock.dto;

import jakarta.persistence.Table;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Table(name="STOCK")
@EqualsAndHashCode(callSuper=false)
@Dataset(name="gds_stock")
public class StockResDto extends BaseTO implements Serializable {
    private String warehouseCode;
    @Id
    private String itemCode;
    private String itemName;
    private String unitOfStock;
    private String safetyAllowanceAmount;
    private String stockAmount;
    private String orderAmount;
    private String inputAmount;
    private String deliveryAmount;
    private String releaseAmount;
    private String totalStockAmount;
}

package kr.co.seoulit.logistics.transvehicle.to;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="ds_vehicle")
public class TransVehicleTO extends BaseTO{
    private String vehicleNumber;
    private String type;
    private String manufacturingCompany;
    private String yearOfManufacture;
    private String loadCapacity;
    private String maintenanceDate;
    private String operator;
    private String fuelType;
    private String transportationStatus;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    @Transient
    private String checked;
}
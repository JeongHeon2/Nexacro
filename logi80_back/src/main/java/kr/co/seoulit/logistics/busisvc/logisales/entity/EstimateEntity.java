package kr.co.seoulit.logistics.busisvc.logisales.entity;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.BaseTO;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ESTIMATE")
@Dataset(name = "gds_estimate")
public class EstimateEntity extends BaseTO implements Persistable<String>, Serializable {

    @Id
    private String estimateNo;
    private String estimateRequester;
    private String description;
    private String contractStatus;
    private String customerCode;
    private String personCodeInCharge;
    private String estimateDate;
    private String effectiveDate;

    @Transient
    private String status;

    @Transient
    private String personNameCharge;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "estimateNo")
    private List<EstimateDetailEntity> estimateDetailEntityList;


    @Transient
    @RemoveColumn
    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isNew() {
        // TODO Auto-generated method stub
        return createdDate == null;
    }

}
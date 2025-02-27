package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:44+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class EstimateDetailReqMapstructImpl implements EstimateDetailReqMapstruct {

    @Override
    public EstimateDetailEntity toEntity(EstimateDetailReqDto dto) {
        if ( dto == null ) {
            return null;
        }

        EstimateDetailEntity estimateDetailEntity = new EstimateDetailEntity();

        estimateDetailEntity.setEstimateDetailNo( dto.getEstimateDetailNo() );
        estimateDetailEntity.setUnitOfEstimate( dto.getUnitOfEstimate() );
        estimateDetailEntity.setEstimateNo( dto.getEstimateNo() );
        estimateDetailEntity.setUnitPriceOfEstimate( dto.getUnitPriceOfEstimate() );
        estimateDetailEntity.setSumPriceOfEstimate( dto.getSumPriceOfEstimate() );
        estimateDetailEntity.setDescription( dto.getDescription() );
        estimateDetailEntity.setItemCode( dto.getItemCode() );
        estimateDetailEntity.setEstimateAmount( dto.getEstimateAmount() );
        estimateDetailEntity.setDueDateOfEstimate( dto.getDueDateOfEstimate() );
        estimateDetailEntity.setItemName( dto.getItemName() );
        estimateDetailEntity.setChecked( dto.getChecked() );
        estimateDetailEntity.setStatus( dto.getStatus() );

        return estimateDetailEntity;
    }

    @Override
    public List<EstimateDetailEntity> toEntity(List<EstimateDetailReqDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<EstimateDetailEntity> list = new ArrayList<EstimateDetailEntity>( dtos.size() );
        for ( EstimateDetailReqDto estimateDetailReqDto : dtos ) {
            list.add( toEntity( estimateDetailReqDto ) );
        }

        return list;
    }

    @Override
    public ArrayList<EstimateDetailEntity> toEntity(ArrayList<EstimateDetailReqDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        ArrayList<EstimateDetailEntity> arrayList = new ArrayList<EstimateDetailEntity>();
        for ( EstimateDetailReqDto estimateDetailReqDto : dtos ) {
            arrayList.add( toEntity( estimateDetailReqDto ) );
        }

        return arrayList;
    }
}

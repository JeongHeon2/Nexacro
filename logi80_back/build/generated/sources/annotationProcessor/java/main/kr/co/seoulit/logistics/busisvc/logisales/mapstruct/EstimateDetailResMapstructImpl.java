package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class EstimateDetailResMapstructImpl implements EstimateDetailResMapstruct {

    @Override
    public EstimateDetailResDto toDto(EstimateDetailEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EstimateDetailResDto estimateDetailResDto = new EstimateDetailResDto();

        estimateDetailResDto.setStatus( entity.getStatus() );
        estimateDetailResDto.setChecked( entity.getChecked() );
        estimateDetailResDto.setEstimateDetailNo( entity.getEstimateDetailNo() );
        estimateDetailResDto.setEstimateNo( entity.getEstimateNo() );
        estimateDetailResDto.setItemCode( entity.getItemCode() );
        estimateDetailResDto.setItemName( entity.getItemName() );
        estimateDetailResDto.setUnitOfEstimate( entity.getUnitOfEstimate() );
        estimateDetailResDto.setDueDateOfEstimate( entity.getDueDateOfEstimate() );
        estimateDetailResDto.setEstimateAmount( entity.getEstimateAmount() );
        estimateDetailResDto.setUnitPriceOfEstimate( entity.getUnitPriceOfEstimate() );
        estimateDetailResDto.setSumPriceOfEstimate( entity.getSumPriceOfEstimate() );
        estimateDetailResDto.setDescription( entity.getDescription() );

        return estimateDetailResDto;
    }

    @Override
    public List<EstimateDetailResDto> toDto(List<EstimateDetailEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EstimateDetailResDto> list = new ArrayList<EstimateDetailResDto>( entities.size() );
        for ( EstimateDetailEntity estimateDetailEntity : entities ) {
            list.add( toDto( estimateDetailEntity ) );
        }

        return list;
    }

    @Override
    public ArrayList<EstimateDetailResDto> toDto(ArrayList<EstimateDetailEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<EstimateDetailResDto> arrayList = new ArrayList<EstimateDetailResDto>();
        for ( EstimateDetailEntity estimateDetailEntity : entities ) {
            arrayList.add( toDto( estimateDetailEntity ) );
        }

        return arrayList;
    }
}

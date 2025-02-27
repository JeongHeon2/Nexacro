package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class EstimateResMapstructImpl implements EstimateResMapstruct {

    @Override
    public EstimateResDto toDto(EstimateEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EstimateResDto estimateResDto = new EstimateResDto();

        estimateResDto.setEstimateNo( entity.getEstimateNo() );
        estimateResDto.setCustomerCode( entity.getCustomerCode() );
        estimateResDto.setEstimateDate( entity.getEstimateDate() );
        estimateResDto.setContractStatus( entity.getContractStatus() );
        estimateResDto.setEstimateRequester( entity.getEstimateRequester() );
        estimateResDto.setEffectiveDate( entity.getEffectiveDate() );
        estimateResDto.setPersonCodeInCharge( entity.getPersonCodeInCharge() );
        estimateResDto.setDescription( entity.getDescription() );

        return estimateResDto;
    }

    @Override
    public List<EstimateResDto> toDto(List<EstimateEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EstimateResDto> list = new ArrayList<EstimateResDto>( entities.size() );
        for ( EstimateEntity estimateEntity : entities ) {
            list.add( toDto( estimateEntity ) );
        }

        return list;
    }

    @Override
    public ArrayList<EstimateResDto> toDto(ArrayList<EstimateEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<EstimateResDto> arrayList = new ArrayList<EstimateResDto>();
        for ( EstimateEntity estimateEntity : entities ) {
            arrayList.add( toDto( estimateEntity ) );
        }

        return arrayList;
    }
}

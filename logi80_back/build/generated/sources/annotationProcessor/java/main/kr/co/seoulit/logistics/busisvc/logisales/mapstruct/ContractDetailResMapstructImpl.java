package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ContractDetailResMapstructImpl implements ContractDetailResMapstruct {

    @Override
    public ContractDetailResDto toDto(ContractDetailEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ContractDetailResDto contractDetailResDto = new ContractDetailResDto();

        contractDetailResDto.setStatus( entity.getStatus() );
        contractDetailResDto.setChecked( entity.getChecked() );
        contractDetailResDto.setContractDetailNo( entity.getContractDetailNo() );
        contractDetailResDto.setContractNo( entity.getContractNo() );
        contractDetailResDto.setItemCode( entity.getItemCode() );
        contractDetailResDto.setItemName( entity.getItemName() );
        contractDetailResDto.setUnitOfContract( entity.getUnitOfContract() );
        contractDetailResDto.setDueDateOfContract( entity.getDueDateOfContract() );
        contractDetailResDto.setEstimateAmount( entity.getEstimateAmount() );
        contractDetailResDto.setStockAmountUse( entity.getStockAmountUse() );
        contractDetailResDto.setProductionRequirement( entity.getProductionRequirement() );
        contractDetailResDto.setUnitPriceOfContract( entity.getUnitPriceOfContract() );
        contractDetailResDto.setSumPriceOfContract( entity.getSumPriceOfContract() );
        contractDetailResDto.setProcessingStatus( entity.getProcessingStatus() );
        contractDetailResDto.setOperationCompletedStatus( entity.getOperationCompletedStatus() );
        contractDetailResDto.setDeliveryCompletionStatus( entity.getDeliveryCompletionStatus() );
        contractDetailResDto.setReleaseCompletionStatus( entity.getReleaseCompletionStatus() );
        contractDetailResDto.setDescription( entity.getDescription() );

        return contractDetailResDto;
    }

    @Override
    public List<ContractDetailResDto> toDto(List<ContractDetailEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ContractDetailResDto> list = new ArrayList<ContractDetailResDto>( entities.size() );
        for ( ContractDetailEntity contractDetailEntity : entities ) {
            list.add( toDto( contractDetailEntity ) );
        }

        return list;
    }

    @Override
    public ArrayList<ContractDetailResDto> toDto(ArrayList<ContractDetailEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<ContractDetailResDto> arrayList = new ArrayList<ContractDetailResDto>();
        for ( ContractDetailEntity contractDetailEntity : entities ) {
            arrayList.add( toDto( contractDetailEntity ) );
        }

        return arrayList;
    }
}

package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ContractResMapstructImpl implements ContractResMapstruct {

    @Override
    public ContractEntity toEntity(ContractResDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContractEntity contractEntity = new ContractEntity();

        contractEntity.setStatus( dto.getStatus() );
        contractEntity.setContractNo( dto.getContractNo() );
        contractEntity.setContractType( dto.getContractType() );
        contractEntity.setEstimateNo( dto.getEstimateNo() );
        contractEntity.setContractDate( dto.getContractDate() );
        contractEntity.setDescription( dto.getDescription() );
        contractEntity.setContractRequester( dto.getContractRequester() );
        contractEntity.setCustomerCode( dto.getCustomerCode() );
        contractEntity.setPersonCodeInCharge( dto.getPersonCodeInCharge() );

        return contractEntity;
    }

    @Override
    public List<ContractEntity> toEntity(List<ContractResDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ContractEntity> list = new ArrayList<ContractEntity>( dtos.size() );
        for ( ContractResDto contractResDto : dtos ) {
            list.add( toEntity( contractResDto ) );
        }

        return list;
    }

    @Override
    public ArrayList<ContractEntity> toEntity(ArrayList<ContractResDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        ArrayList<ContractEntity> arrayList = new ArrayList<ContractEntity>();
        for ( ContractResDto contractResDto : dtos ) {
            arrayList.add( toEntity( contractResDto ) );
        }

        return arrayList;
    }
}

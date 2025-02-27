package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ContractInfoResMapstructImpl implements ContractInfoResMapstruct {

    @Override
    public ContractInfoResDto toDto(ContractInfoDto entity) {
        if ( entity == null ) {
            return null;
        }

        ContractInfoResDto contractInfoResDto = new ContractInfoResDto();

        contractInfoResDto.setStatus( entity.getStatus() );
        contractInfoResDto.setContractNo( entity.getContractNo() );
        contractInfoResDto.setEstimateNo( entity.getEstimateNo() );
        contractInfoResDto.setContractType( entity.getContractType() );
        contractInfoResDto.setContractTypeName( entity.getContractTypeName() );
        contractInfoResDto.setCustomerCode( entity.getCustomerCode() );
        contractInfoResDto.setCustomerName( entity.getCustomerName() );
        contractInfoResDto.setEstimateDate( entity.getEstimateDate() );
        contractInfoResDto.setContractDate( entity.getContractDate() );
        contractInfoResDto.setContractRequester( entity.getContractRequester() );
        contractInfoResDto.setPersonCodeInCharge( entity.getPersonCodeInCharge() );
        contractInfoResDto.setEmpNameInCharge( entity.getEmpNameInCharge() );
        contractInfoResDto.setDeliveryCompletionStatus( entity.getDeliveryCompletionStatus() );
        contractInfoResDto.setReleaseCompletionStatus( entity.getReleaseCompletionStatus() );
        contractInfoResDto.setDescription( entity.getDescription() );

        return contractInfoResDto;
    }

    @Override
    public List<ContractInfoResDto> toDto(List<ContractInfoDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ContractInfoResDto> list = new ArrayList<ContractInfoResDto>( entities.size() );
        for ( ContractInfoDto contractInfoDto : entities ) {
            list.add( toDto( contractInfoDto ) );
        }

        return list;
    }

    @Override
    public ArrayList<ContractInfoResDto> toDto(ArrayList<ContractInfoDto> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<ContractInfoResDto> arrayList = new ArrayList<ContractInfoResDto>();
        for ( ContractInfoDto contractInfoDto : entities ) {
            arrayList.add( toDto( contractInfoDto ) );
        }

        return arrayList;
    }
}

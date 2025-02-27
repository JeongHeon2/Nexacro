package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractResMapstruct extends EntityReqMapper<ContractEntity, ContractResDto> {
}

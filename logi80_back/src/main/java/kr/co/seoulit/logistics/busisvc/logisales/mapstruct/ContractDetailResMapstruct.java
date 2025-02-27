package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDetailResMapstruct extends EntityResMapper<ContractDetailEntity, ContractDetailResDto> {
}

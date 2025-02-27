package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoDto;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ContractInfoResMapstruct extends EntityResMapper<ContractInfoDto, ContractInfoResDto> {
}

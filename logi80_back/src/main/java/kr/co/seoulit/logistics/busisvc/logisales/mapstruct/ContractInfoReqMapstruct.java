package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoDto;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ContractInfoReqMapstruct extends EntityReqMapper<ContractInfoDto, ContractInfoReqDto> {
}

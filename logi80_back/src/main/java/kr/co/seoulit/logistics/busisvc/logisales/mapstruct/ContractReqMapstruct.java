package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractReqMapstruct extends EntityReqMapper<ContractEntity, ContractReqDto> {
}

package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDetailReqMapstruct extends EntityReqMapper<ContractDetailEntity, ContractDetailReqDto> {
}

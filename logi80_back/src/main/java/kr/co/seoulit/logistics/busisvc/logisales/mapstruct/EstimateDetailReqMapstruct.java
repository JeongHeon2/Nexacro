package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstimateDetailReqMapstruct extends EntityReqMapper<EstimateDetailEntity, EstimateDetailReqDto> {

}

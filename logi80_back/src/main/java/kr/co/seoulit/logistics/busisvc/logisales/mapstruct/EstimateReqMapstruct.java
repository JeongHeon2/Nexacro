package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EstimateReqMapstruct extends EntityReqMapper<EstimateEntity, EstimateReqDto> {

    @Override
    EstimateEntity toEntity(EstimateReqDto dto);

    @Override
    List<EstimateEntity> toEntity(List<EstimateReqDto> dtos);

    @Override
    ArrayList<EstimateEntity> toEntity(ArrayList<EstimateReqDto> dtos);
}

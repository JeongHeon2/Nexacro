package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EstimateDetailResMapstruct extends EntityResMapper<EstimateDetailEntity, EstimateDetailResDto> {

    @Override
    EstimateDetailResDto toDto(EstimateDetailEntity entity);

    @Override
    List<EstimateDetailResDto> toDto(List<EstimateDetailEntity> entities);

    @Override
    ArrayList<EstimateDetailResDto> toDto(ArrayList<EstimateDetailEntity> entities);
}

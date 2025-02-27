package kr.co.seoulit.logistics.busisvc.logisales.mapstruct;

import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EstimateResMapstruct extends EntityResMapper<EstimateEntity,EstimateResDto>{

    EstimateResDto toDto(EstimateEntity entity);


    List<EstimateResDto> toDto(List<EstimateEntity> entities);

    ArrayList<EstimateResDto> toDto(ArrayList<EstimateEntity> entities);



}

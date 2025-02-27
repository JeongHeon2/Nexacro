package kr.co.seoulit.logistics.purcstosvc.stock.mapStruct;

import kr.co.seoulit.logistics.purcstosvc.stock.dto.StockResDto;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockResMapStruct extends EntityResMapper<StockEntity, StockResDto> {

    @Override
    StockResDto toDto(StockEntity entity);

    @Override
    List<StockResDto> toDto(List<StockEntity> entities);

}

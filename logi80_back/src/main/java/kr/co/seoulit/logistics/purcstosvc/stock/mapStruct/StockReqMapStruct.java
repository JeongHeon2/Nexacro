package kr.co.seoulit.logistics.purcstosvc.stock.mapStruct;

import kr.co.seoulit.logistics.purcstosvc.stock.dto.StockRepDto;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockReqMapStruct extends EntityReqMapper<StockEntity, StockRepDto> {

    @Override
    StockEntity toEntity(StockRepDto dto);

    @Override
    List<StockEntity> toEntity(List<StockRepDto> entities);

}
